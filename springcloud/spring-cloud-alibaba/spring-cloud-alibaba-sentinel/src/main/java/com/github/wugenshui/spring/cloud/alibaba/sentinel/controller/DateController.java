package com.github.wugenshui.spring.cloud.alibaba.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 默认HTTP 埋点
 * Sentinel starter 默认为所有的 HTTP 服务提供了限流埋点，如果只想对 HTTP 服务进行限流，那么只需要引入依赖，无需修改代码
 *
 * @author : chenbo
 * @date : 2021-01-14
 */
@RestController
@RequestMapping
public class DateController {

    @GetMapping("/date")
    public Date get() {
        return new Date();
    }
}
