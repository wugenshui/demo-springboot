package com.chenbo.demo.spring.cloud.alibaba.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-01-14
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }
}
