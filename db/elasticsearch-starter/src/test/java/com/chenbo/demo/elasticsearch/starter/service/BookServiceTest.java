package com.github.wugenshui.elasticsearch.starter.service;


import com.github.wugenshui.elasticsearch.starter.entity.BookBean;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-05-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private BookService bookService;

    @Before
    public void initData() {
        //deleteIndex();
        //createIndex();
        List<BookBean> all = bookService.findAll();
        System.out.println("all = " + all);
        if (CollectionUtils.isEmpty(all)) {
            bookService.save(new BookBean("1", "ES入门教程1", "张三", "2016-10-01", 1, LocalDateTime.now()));
            bookService.save(new BookBean("2", "ES入门教程2", "李四", "2017-10-01", 1, LocalDateTime.now()));
            bookService.save(new BookBean("3", "ES入门教程3", "王五", "2018-10-01", 1, LocalDateTime.now()));
            bookService.save(new BookBean(null, "ES入门教程4", "何六", "2019-10-01", 0, LocalDateTime.now()));
            bookService.save(new BookBean(null, "ES入门教程4", "张七", "2019-10-01", 0, LocalDateTime.now()));
        }
    }

    @Test
    public void findAll() {
        List<BookBean> all = bookService.findAll();
        System.out.println("all = " + all);
    }

    @Test
    public void findById() {
        System.out.println("elasticsearchRestTemplate = " + bookService.findById("2"));
    }

    @Test
    public void delete() {
        bookService.delete(BookBean.builder().id("1").build());
    }

    @Test
    public void findOne() {
        System.out.println("elasticsearchRestTemplate = " + bookService.findOne("2"));
    }

    @Test
    public void findByAuthor() {
        Page<BookBean> byAuthor = bookService.findByAuthor("张三", PageRequest.of(1, 20));
        System.out.println(byAuthor.getContent());

        byAuthor = bookService.findByAuthor("张", PageRequest.of(0, 20));
        System.out.println(byAuthor.getContent());
    }

    @Test
    public void findByTitle() {
        Page<BookBean> byTitle = bookService.findByTitle("ES入门教程1", PageRequest.of(0, 20));
        System.out.println("ES入门教程1" + byTitle.getContent());

        byTitle = bookService.findByTitle("ES入门教程", PageRequest.of(0, 20));
        System.out.println("ES入门教程" + byTitle.getContent());

        byTitle = bookService.findByTitle("ES", PageRequest.of(0, 20));
        System.out.println("ES" + byTitle.getContent());

        byTitle = bookService.findByTitle("入门", PageRequest.of(0, 20));
        System.out.println("入门" + byTitle.getContent());

        byTitle = bookService.findByTitle("入", PageRequest.of(0, 20));
        System.out.println("入" + byTitle.getContent());
    }

    private void deleteIndex() {
        System.out.println("删除索引");
        elasticsearchOperations.indexOps(BookBean.class).delete();
    }

    private void createIndex() {
        System.out.println("创建索引");
        elasticsearchOperations.indexOps(BookBean.class).create();
    }

    @Test
    public void reactiveElasticsearchTemplateTest() {
        // 聚合查询
        String sexAggName = "sexAgg";
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.addAggregation(AggregationBuilders.terms(sexAggName).field("sex"));
        SearchHits<BookBean> search = elasticsearchRestTemplate.search(queryBuilder.build(), BookBean.class);

        // 解析聚合
        Aggregations aggregations = search.getAggregations();
        // 获取指定名称的聚合
        ParsedLongTerms terms = aggregations.get(sexAggName);
        // 获取桶
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        // 遍历打印
        Map<String, Integer> map = new HashMap<>();
        for (Terms.Bucket bucket : buckets) {
            map.put(bucket.getKeyAsString(), (int) bucket.getDocCount());
            System.out.println("key = " + bucket.getKeyAsString());
            System.out.println("DocCount = " + bucket.getDocCount());
        }
        System.out.println("map = " + map);
    }
}