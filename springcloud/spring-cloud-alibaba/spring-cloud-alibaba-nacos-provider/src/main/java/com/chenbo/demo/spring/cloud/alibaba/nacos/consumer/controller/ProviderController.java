package com.chenbo.demo.spring.cloud.alibaba.nacos.consumer.controller;

import com.chenbo.demo.spring.cloud.alibaba.nacos.consumer.utils.IPUtils;
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
public class ProviderController {

    @Value("${value:default}")
    private String value;

    @GetMapping
    public String getValue() {
        return value;
    }


    @GetMapping("/ip")
    public String getIP() {
        return IPUtils.getLinuxHostAddress();
    }
}
