package com.chenbo.demo.redis.cache.controller;

import com.chenbo.demo.redis.cache.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@RestController
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("save")
    public void save() {
        redisTemplate.opsForValue().set("name", User.builder().age(18).build());
    }

    @Cacheable(value = "user")
    @GetMapping
    public User test() throws InterruptedException {
        Thread.sleep(2000);
        User user = User.builder().age(18).build();
        return user;
    }

    @CachePut(value = "put")
    @GetMapping("/put")
    public User put() throws InterruptedException {
        Thread.sleep(2000);
        User user = User.builder().age(18).build();
        return user;
    }

    @Cacheable("num")
    @GetMapping("/{num}")
    public String square(@PathVariable int num) {
        return "num * num = " + (num * num);
    }

    @CacheEvict(value = "num", allEntries = true)
    @GetMapping("/delete/{num}")
    public String delete(@PathVariable int num) {
        return "delete:" + num;
    }

    @Cacheable("ab")
    @GetMapping("/{a}/{b}")
    public String square(@PathVariable int a, @PathVariable int b) {
        return "a * b = " + (a * b);
    }

}
