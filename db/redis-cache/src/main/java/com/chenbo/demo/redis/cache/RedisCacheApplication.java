package com.chenbo.demo.redis.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@EnableCaching
@MapperScan("com.chenbo.demo.redis.cache.mapper")
@SpringBootApplication
public class RedisCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }
}

