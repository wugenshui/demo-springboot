package com.chenbo.demo.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-01-08
 */
@RestController
@RefreshScope
@RequestMapping("/")
public class RefreshController {

    @Value("${value:default}")
    private String value;

    @GetMapping
    public String getValue() {
        return value;
    }
}
