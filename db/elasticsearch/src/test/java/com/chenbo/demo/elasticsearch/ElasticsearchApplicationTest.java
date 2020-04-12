package com.chenbo.demo.elasticsearch;


import com.chenbo.demo.elasticsearch.entity.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2020-04-10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchApplicationTest {

    private static final String INDEX_TEST = "news";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private Gson gson;

    @Autowired
    private RestHighLevelClient client;

    @Before
    public void init() {
        gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
    }

    @Test
    public void initDataTest() throws IOException {
        for (int i = 0; i < 20; i++) {
            News news = new News("title" + i, "tag", i, new Date());

            IndexRequest indexRequest = new IndexRequest(INDEX_TEST)
                    .id(i + "")
                    .source(gson.toJson(news), XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println("indexResponse = " + indexResponse);
        }

    }

    @Test
    public void queryTest() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("tag", "tag"));
        sourceBuilder.sort("count", SortOrder.DESC);
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse = " + searchResponse);
    }

    @Test
    public void addTest() throws IOException {
        // 添加索引
        IndexRequest indexRequest = new IndexRequest(INDEX_TEST)
                .id("1")
                .source("user", "张三",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("indexResponse = " + indexResponse);

        indexRequest = new IndexRequest(INDEX_TEST)
                .id("2")
                .source("user", "李四",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("indexResponse = " + indexResponse);

        // 查询
        GetRequest getRequest = new GetRequest(INDEX_TEST, "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("getResponse = " + getResponse);

        getRequest = new GetRequest(INDEX_TEST, "2");
        getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("getResponse = " + getResponse);

    }

    @Test
    public void searchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("全部查询 = " + searchResponse);
    }


    @Test
    public void indexExistsTest() throws IOException {
        GetIndexRequest request = new GetIndexRequest(INDEX_TEST);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("check Index[{}] exists... res={}", INDEX_TEST, exists);
    }

    @Test
    public void esTest() throws IOException {
        // 查询
        String index = "test";
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        Assert.assertFalse(exists);

        // 2. 创建索引
        IndexRequest indexRequest = new IndexRequest("posts");
        indexRequest.id("1");
        String jsonString = "{" +
                "\"user\":\"chargedot\"," +
                "\"Date\":\"2019-10-25\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexRequest.source(jsonString, XContentType.JSON);

        // 3. 条件设置
        indexRequest.routing("routing");
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        indexRequest.setRefreshPolicy("wait_for");

        // 4. 监听
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("indexResponse succeed!");
            }

            @Override
            public void onFailure(Exception e) {
                log.info("indexResponse failed!");
            }
        };

        client.close();
    }

    @After
    public void showAllTest() throws IOException, InterruptedException {
        // 睡眠2秒保证数据查出
        Thread.sleep(2000);
        SearchRequest searchRequest = new SearchRequest(INDEX_TEST);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println("当前文档数量：" + searchHits.length);
        for (SearchHit searchHit : searchHits) {
            News news = gson.fromJson(searchHit.getSourceAsString(), News.class);
            System.out.println(news);
        }
    }
}