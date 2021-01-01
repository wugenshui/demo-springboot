package com.chenbo.demo.redis.cache.controller;

import com.chenbo.demo.redis.cache.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@RestController
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @Cacheable("cache:user")
    @GetMapping
    public User test() throws InterruptedException, IOException {
        Thread.sleep(3000);
        User user = User.builder().age(18).build();
        return user;
    }
}
