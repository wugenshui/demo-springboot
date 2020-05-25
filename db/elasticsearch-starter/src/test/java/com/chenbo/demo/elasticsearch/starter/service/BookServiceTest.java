package com.chenbo.demo.elasticsearch.starter.service;


import com.chenbo.demo.elasticsearch.starter.entity.BookBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    private BookService bookService;

    @Before
    public void initData() {
        elasticsearchRestTemplate.deleteIndex(BookBean.class);

        elasticsearchRestTemplate.createIndex(BookBean.class);
        //List<BookBean> all = bookService.findAll();
        //System.out.println("all = " + all);
        //if (CollectionUtils.isEmpty(all)) {
        bookService.save(new BookBean("1", "ES入门教程1", "张三", "2016-10-01"));
        bookService.save(new BookBean("2", "ES入门教程2", "李四", "2017-10-01"));
        bookService.save(new BookBean("3", "ES入门教程3", "王五", "2018-10-01"));
        bookService.save(new BookBean(null, "ES入门教程4", "何六", "2019-10-01"));
        bookService.save(new BookBean(null, "ES入门教程4", "张七", "2019-10-01"));
        //}
    }

    @Test
    public void findById() {
        System.out.println("elasticsearchRestTemplate = " + bookService.findById("2"));
    }

    @Test
    public void delete() {
        bookService.delete(new BookBean("1", null, null, null));
    }

    @Test
    public void findOne() {
        System.out.println("elasticsearchRestTemplate = " + bookService.findOne("2"));
    }

    @Test
    public void findAll() {
        List<BookBean> all = bookService.findAll();
        System.out.println("all = " + all);
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
    }

    // 删除索引
    @Test
    public void deleteIndex() {
        elasticsearchRestTemplate.deleteIndex(BookBean.class);
    }
}