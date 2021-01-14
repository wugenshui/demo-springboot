package com.chenbo.demo.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author : chenbo
 * @date : 2021-01-08
 */
@RestController
@RefreshScope
@RequestMapping("/")
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping
    public String getValue() {
        return restTemplate.getForObject("http://nacos-demo", String.class);
    }

    @GetMapping("/ip")
    public String getIP() {
        return restTemplate.getForObject("http://nacos-demo/ip", String.class);
    }
}
