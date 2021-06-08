package com.github.wugenshui.elasticsearch.util;

import com.github.wugenshui.elasticsearch.bean.ElasticsearchConfigBean;
import com.github.wugenshui.elasticsearch.bean.EsPage;
import com.github.wugenshui.elasticsearch.bean.OrderBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Elasticsearch工具类
 *
 * @author : chenbo
 * @date : 2020-04-28
 */
@Slf4j
@Component
public class ElasticsearchUtil {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchConfigBean config;

    private static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private ElasticsearchUtil() {
    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     * @throws IOException
     */
    public boolean existsIndex(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("check Index[{}] exists... res={}", index, exists);
        return exists;
    }

    /**
     * 创建索引
     *
     * @param index       索引名 （索引名必须全小写，否则报错）
     * @param alias       别名
     * @param mappingJson 参数配置字符串（XContentBuilder 或者 Map<String, Object> message）
     * @return
     */
    public boolean createIndex(String index, String alias, String mappingJson) throws IOException {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(mappingJson)) {
            return false;
        }

        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder()
                .put("index.number_of_shards", config.getNumberOfShards())
                .put("index.number_of_replicas", config.getNumberOfReplicas())
        );
        request.setTimeout(TimeValue.timeValueMinutes(2));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        // 设置处理主从复制之前必须处于激活状态到的副本数量
        request.waitForActiveShards(ActiveShardCount.ONE);

        request.mapping(mappingJson, XContentType.JSON);

        if (StringUtils.isEmpty(alias)) {
            request.alias(new Alias(alias));
        }

        CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        if (indexResponse.isAcknowledged()) {
            log.info("es create index=[ {} ] success! ", index);
        } else {
            log.info("es create index=[ {} ] fail! ", index);
        }
        return indexResponse.isAcknowledged();
    }

    /**
     * 删除索引
     *
     * @param index
     * @return
     */
    public boolean deleteIndex(String index) throws IOException {
        if (!existsIndex(index)) {
            return true;
        }

        DeleteIndexRequest request = new DeleteIndexRequest(index);
        request.timeout(TimeValue.timeValueMinutes(2));
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));

        AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);
        boolean acknowledged = deleteIndexResponse.isAcknowledged();
        log.info("!! delete index: {}  result={}", index);
        return acknowledged;
    }

    /**
     * 判断索引别名是否绑定
     *
     * @return
     */
    public boolean checkIndexBindAlias(String index, String alias) throws IOException {
        GetAliasesRequest request = new GetAliasesRequest();
        request.aliases(alias);
        request.indices(index);
        boolean exists = client.indices().existsAlias(request, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 索引绑定别名
     *
     * @param index
     * @param alias
     * @return
     * @throws IOException
     */
    public boolean bindIndexToAlias(String index, String alias) throws IOException {
        if (StringUtils.isEmpty(index) && StringUtils.isEmpty(alias)) {
            IndicesAliasesRequest request = new IndicesAliasesRequest();
            IndicesAliasesRequest.AliasActions aliasAction =
                    new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.ADD)
                            .index(index)
                            .alias(alias);
            request.addAliasAction(aliasAction);
            request.timeout(TimeValue.timeValueMinutes(2));
            request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
            AcknowledgedResponse indicesAliasesResponse = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            return indicesAliasesResponse.isAcknowledged();
        }
        return false;
    }

    /**
     * 索引与别名解除绑定
     *
     * @param index
     * @param alias
     * @return
     * @throws IOException
     */
    public boolean unbindIndexToAlias(String index, String alias) throws IOException {
        if (StringUtils.isEmpty(index) && StringUtils.isEmpty(alias)) {
            IndicesAliasesRequest request = new IndicesAliasesRequest();
            IndicesAliasesRequest.AliasActions aliasAction =
                    new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.REMOVE)
                            .index(index)
                            .alias(alias);
            request.addAliasAction(aliasAction);
            request.timeout(TimeValue.timeValueMinutes(2));
            request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
            AcknowledgedResponse indicesAliasesResponse = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            return indicesAliasesResponse.isAcknowledged();
        }
        return false;
    }


    /**
     * 增加记录
     *
     * @param index
     * @param id
     * @param jsonData
     * @throws IOException
     */
    public IndexResponse add(String index, String id, String jsonData) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(id);
        indexRequest.source(jsonData, XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    /**
     * 批量数据添加，
     *
     * @param map   要增加的数据
     * @param index 索引，类似数据库
     * @return
     */
    public <T> void addBatchData(Map<String, T> map, String index) throws IOException {
        if (map == null || map.isEmpty()) {
            return;
        }
        BulkRequest request = new BulkRequest();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            request.add(new IndexRequest(index).id(entry.getKey()).source(GSON.toJson(entry.getValue()), XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
    }


    /**
     * 通过ID 更新数据
     */
    public void updateDataById(String index, Map<String, Object> doc, String id) throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).id(id).doc(doc);
        if (RestStatus.OK.getStatus() != client.update(updateRequest, RequestOptions.DEFAULT).status().getStatus()) {
            throw new Exception("更新失败！");
        }
    }

    /**
     * 通过script更新
     */
    public BulkByScrollResponse updateByScript(String index, BoolQueryBuilder queryBuilder, Script script) throws IOException {
        UpdateByQueryRequest request = new UpdateByQueryRequest(index);
        request.setQuery(queryBuilder);
        request.setScript(script);
        return client.updateByQuery(request, RequestOptions.DEFAULT);
    }


    /**
     * 根据esId查询数据
     *
     * @param index
     * @param id
     * @return
     * @throws IOException
     */
    public Map<String, Object> getByEsId(String index, String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            return getResponse.getSourceAsMap();
        }

        return null;
    }


    /**
     * 使用分词查询
     *
     * @param index     索引名称
     * @param clz       数据对应实体类
     * @param fields    需要显示的字段，逗号分隔（缺省为全部字段）
     * @param boolQuery 查询条件
     * @return
     */
    public <T> List<T> searchListData(String index, Class<T> clz, String fields, Integer size, List<OrderBean> sortFields, BoolQueryBuilder boolQuery) throws Exception {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if (size == null) {
            size = 10000;
        }
        sourceBuilder.size(size);
        sourceBuilder.query(boolQuery);
        // 需要显示的字段，逗号分隔（缺省为全部字段）
        if (StringUtils.isEmpty(fields)) {
            sourceBuilder.fetchSource(fields.split(","), null);
        }
        // 排序字段
        if (sortFields != null && !sortFields.isEmpty()) {
            for (OrderBean sortField : sortFields) {
                sourceBuilder.sort(sortField.getOrderBy(), sortField.getOrderSort());
            }
        }

        // 设置是否按查询匹配度排序
        sourceBuilder.explain(true);

        SearchRequest request = new SearchRequest();
        // 指定索引
        if (!StringUtils.isEmpty(index)) {
            request.indices(index);
        }
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        if (RestStatus.OK.getStatus() == response.status().getStatus()) {
            long totalHits = response.getHits().getTotalHits().value;
            long length = response.getHits().getHits().length;
            log.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);
            List<T> sourceList = new ArrayList<>();
            for (SearchHit searchHit : response.getHits().getHits()) {
                sourceList.add(GSON.fromJson(searchHit.getSourceAsString(), clz));
            }
            return sourceList;
        }
        return null;
    }


    /**
     * 聚合查询
     */
    public Map<String, Aggregation> searchAggregation(String index, BoolQueryBuilder boolQuery, AggregationBuilder aggregationBuilder) throws Exception {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        sourceBuilder.aggregation(aggregationBuilder);
        // 设置是否按查询匹配度排序
        sourceBuilder.explain(true);

        SearchRequest request = new SearchRequest();
        // 指定索引
        if (!StringUtils.isEmpty(index)) {
            request.indices(index);
        }
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        if (RestStatus.OK.getStatus() == response.status().getStatus()) {
            return response.getAggregations().asMap();
        }
        return null;
    }

    public Long count(String idx, BoolQueryBuilder queryBuilder) throws Exception {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        CountRequest countRequest = new CountRequest();
        countRequest.indices(idx).source(sourceBuilder);
        CountResponse countResponse = client.count(countRequest, RequestOptions.DEFAULT);
        return countResponse.getCount();
    }


    /**
     * 使用分词查询,并分页
     *
     * @param index       索引名称
     * @param clz         数据对应实体类
     * @param currentPage 当前页
     * @param pageSize    每页显示条数
     * @param fields      需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortFields  排序字段
     * @param boolQuery   查询条件
     * @return
     */
    public <T> EsPage<T> searchDataPage(String index, Class<T> clz, int currentPage, int pageSize,
                                        String fields, List<OrderBean> sortFields, BoolQueryBuilder boolQuery) throws Exception {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQuery);
        // 需要显示的字段，逗号分隔（缺省为全部字段）
        if (!StringUtils.isEmpty(fields)) {
            sourceBuilder.fetchSource(fields.split(","), null);
        }
        // 排序字段
        if (sortFields != null && !sortFields.isEmpty()) {
            for (OrderBean sortField : sortFields) {
                sourceBuilder.sort(sortField.getOrderBy(), sortField.getOrderSort());
            }
        }
        // 分页应用
        int firstSize = (currentPage - 1) * pageSize;
        sourceBuilder.from(firstSize).size(pageSize);
        // 设置是否按查询匹配度排序
        sourceBuilder.explain(true);

        SearchRequest request = new SearchRequest();
        // 指定索引
        if (!StringUtils.isEmpty(index)) {
            request.indices(index);
        }
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        if (RestStatus.OK.getStatus() == response.status().getStatus()) {
            long totalHits = response.getHits().getTotalHits().value;
            long length = response.getHits().getHits().length;
            log.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);
            List<T> sourceList = new ArrayList<>();
            for (SearchHit searchHit : response.getHits().getHits()) {
                sourceList.add(GSON.fromJson(searchHit.getSourceAsString(), clz));
            }
            return new EsPage<>(currentPage, pageSize, (int) totalHits, sourceList);
        }
        return null;
    }

}
