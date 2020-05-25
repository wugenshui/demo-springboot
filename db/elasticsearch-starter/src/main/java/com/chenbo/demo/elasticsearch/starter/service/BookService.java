package com.chenbo.demo.elasticsearch.starter.service;

import com.chenbo.demo.elasticsearch.starter.entity.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2020-05-25
 */
public interface BookService {

    Optional<BookBean> findById(String id);

    BookBean save(BookBean blog);

    void delete(BookBean blog);

    Optional<BookBean> findOne(String id);

    List<BookBean> findAll();

    Page<BookBean> findByAuthor(String author, PageRequest pageRequest);

    Page<BookBean> findByTitle(String title, PageRequest pageRequest);

}