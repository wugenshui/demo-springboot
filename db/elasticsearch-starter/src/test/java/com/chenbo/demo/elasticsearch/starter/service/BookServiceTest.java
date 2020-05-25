package com.chenbo.demo.elasticsearch.starter.service;


import com.chenbo.demo.elasticsearch.starter.entity.BookBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private BookService bookService;

    @Before
    public void initData() {
        //List<BookBean> all = bookService.findAll();
        //System.out.println("all = " + all);
        //if (CollectionUtils.isEmpty(all)) {
            bookService.save(new BookBean("1", "ES入门教程1", "张三", "2016-10-01"));
            bookService.save(new BookBean("2", "ES入门教程2", "李四", "2017-10-01"));
            bookService.save(new BookBean("3", "ES入门教程3", "王五", "2018-10-01"));
            bookService.save(new BookBean(null, "ES入门教程4", "何六", "2019-10-01"));
        //}
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
        List<BookBean> all = bookService.findAll();
        System.out.println("all = " + all);
    }

    @Test
    public void findByAuthor() {
    }

    @Test
    public void findByTitle() {
    }
}