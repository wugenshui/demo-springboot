package com.chenbo.demo.redission.starter.controller;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2021-01-18
 */
@RestController
@RequestMapping
public class TestController {
    private RedissonClient redissonClient;

    private TestController(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @GetMapping("/save/{username}")
    public String get(@PathVariable String username) {
        RBlockingQueue<String> queue = redissonClient.getBlockingQueue("anyQueue");
        queue.offer(username);
        return "ok";
    }
}
