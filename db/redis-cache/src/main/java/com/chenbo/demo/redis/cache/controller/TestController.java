package com.chenbo.demo.redis.cache.controller;

import com.chenbo.demo.redis.cache.entity.User;
import com.chenbo.demo.redis.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/nocache")
    public List<User> nocachetest() throws InterruptedException {
        //Thread.sleep(3000);
        return userMapper.findAll();
    }

    @Cacheable("cache:user")
    @GetMapping("/cache")
    public List<User> test() throws InterruptedException, IOException {
        //Thread.sleep(3000);

        ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
        operations.set("test", userMapper.findAll());
        List<User> test = operations.get("test");
        System.out.println(test);
        return userMapper.findAll();
    }
}
