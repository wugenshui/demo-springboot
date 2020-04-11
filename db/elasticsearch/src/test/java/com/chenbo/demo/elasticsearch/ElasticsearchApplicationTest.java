package com.chenbo.demo.elasticsearch;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author : chenbo
 * @date : 2020-04-10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchApplicationTest {

    @Autowired
    private RestHighLevelClient client;

    @Before
    public void addTest() {

    }

    @Test
    public void findTest() throws IOException {
        String index = "test";
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("check Index[{}] exists... res={}", index, exists);
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
}