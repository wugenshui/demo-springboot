package com.chenbo.demo.elasticsearch.starter.service.impl;

import com.chenbo.demo.elasticsearch.starter.dao.BookRepository;
import com.chenbo.demo.elasticsearch.starter.entity.BookBean;
import com.chenbo.demo.elasticsearch.starter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2020-05-25
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookRepository")
    private BookRepository bookRepository;


    @Override
    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    @Override
    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }

    @Override
    public void delete(BookBean blog) {
        bookRepository.delete(blog);
    }

    @Override
    public Optional<BookBean> findOne(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookBean> findAll() {
        return ((PageImpl) bookRepository.findAll()).getContent();
    }

    @Override
    public Page<BookBean> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    @Override
    public Page<BookBean> findByTitle(String title, PageRequest pageRequest) {
        return bookRepository.findByTitle(title, pageRequest);
    }
}