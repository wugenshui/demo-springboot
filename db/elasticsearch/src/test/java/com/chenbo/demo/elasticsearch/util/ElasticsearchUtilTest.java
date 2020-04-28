package com.chenbo.demo.elasticsearch.util;


import com.chenbo.demo.elasticsearch.entity.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.script.Script;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2020-04-28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchUtilTest {

    private String INDEX = "news";
    private static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Autowired
    private ElasticsearchUtil es;

    @Before
    public void before() {
        INDEX += new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    @Test
    public void existsIndex() throws IOException {
        Boolean bool = es.existsIndex(INDEX);
        Assert.assertFalse(bool);
    }

    @Test
    public void createIndex() throws IOException {
        Boolean bool = es.createIndex(INDEX, null, null);
        Assert.assertFalse(bool);
    }

    @Test
    public void deleteIndex() {
    }

    @Test
    public void checkIndexBindAlias() {
    }

    @Test
    public void bindIndexToAlias() {
    }

    @Test
    public void unbindIndexToAlias() {
    }

    @Test
    public void add() throws IOException {
        for (int i = 0; i < 10; i++) {
            News news = new News("新闻", "tag", i, new Date());
            IndexResponse response = es.add(INDEX, null, GSON.toJson(news));
            System.out.println(response);
        }
    }

    @Test
    public void addBatchData() {
    }

    @Test
    public void updateDataById() {
    }

    @Test
    public void updateByScript() throws IOException {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.filter(QueryBuilders.termQuery("count", 0));
        //queryBuilder.filter(QueryBuilders.idsQuery().addIds("xLnxvnEB1ODT-qsVsGyf", "xbnxvnEB1ODT-qsVsmx2", "3", "4", "5"));
        String newTitle = "1新闻";
        Script updateScript = new Script("ctx._source['title']='" + newTitle + "';");
        BulkByScrollResponse response = es.updateByScript(INDEX, queryBuilder, updateScript);
        System.out.println(response);
    }

    @Test
    public void getByEsId() {
    }

    @Test
    public void searchListData() {
    }

    @Test
    public void searchAggregation() {
    }

    @Test
    public void count() {
    }

    @Test
    public void searchDataPage() {
    }
}