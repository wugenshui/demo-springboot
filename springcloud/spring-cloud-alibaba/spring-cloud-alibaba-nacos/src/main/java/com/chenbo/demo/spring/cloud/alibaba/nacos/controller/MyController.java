package com.chenbo.demo.spring.cloud.alibaba.nacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-01-08
 */
@RestController
@RequestMapping("/")
public class MyController {

    @GetMapping
    public String get() {
        return "test";
    }
}
