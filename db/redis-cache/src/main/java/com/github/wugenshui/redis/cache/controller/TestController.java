package com.github.wugenshui.redis.cache.controller;

import com.github.wugenshui.redis.cache.entity.User;
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

    /**
     * 固定主键缓存:   cache:user::com.github.wugenshui.redis.cache.controller.TestControllertest
     */
    @Cacheable(value = "user")
    @GetMapping
    public User test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return User.builder().name("张三").age(18).build();
    }

    /**
     * 固定主键缓存:   cache:user::com.github.wugenshui.redis.cache.controller.TestControllerput
     */
    @CachePut(value = "put")
    @GetMapping("/put")
    public User put() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = User.builder().name("put").age(18).build();
        return user;
    }

    @Cacheable(value = "num", key = "#num")
    @GetMapping("/{num}")
    public String square(@PathVariable int num) {
        return "num * num = " + (num * num);
    }

    @CacheEvict(value = "num", key = "#num")
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
