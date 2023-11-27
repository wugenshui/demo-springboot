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
     * 1. 当第一次调用时，会将方法的返回值作为value存入缓存中
     * 2. 再次调用此方法时，会从缓存中取出数据，而不会再次执行方法
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
     * 1. 每次调用时，都会将方法的返回值作为value存入缓存中
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

    /**
     * 参数作为缓存的key：      cache:num::1
     */
    @Cacheable(value = "num", key = "#num")
    @GetMapping("/{num}")
    public String square(@PathVariable int num) {
        return "num * num = " + (num * num);
    }

    /**
     * 删除单个缓存：      cache:num::1
     */
    @CacheEvict(value = "num", key = "#num")
    @GetMapping("/delete/{num}")
    public String delete(@PathVariable int num) {
        return "delete:" + num;
    }

    /**
     * 删除全部缓存：      cache:num::*
     */
    @CacheEvict(value = "num", allEntries = true)
    @GetMapping("/delete")
    public String deleteAll() {
        return "deleteAll";
    }

    /**
     * 多个参数作为缓存的key：      cache:ab::com.github.wugenshui.redis.cache.controller.TestControllersquare-2-1
     */
    @Cacheable("ab")
    @GetMapping("/{a}/{b}")
    public String square(@PathVariable int a, @PathVariable int b) {
        return "a * b = " + (a * b);
    }

}
