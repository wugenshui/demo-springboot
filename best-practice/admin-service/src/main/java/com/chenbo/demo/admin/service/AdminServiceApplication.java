package com.chenbo.demo.admin.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 最佳实践项目
 *
 * @author : chenbo
 * @date : 2020-02-13
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chenbo.demo.admin.service.mapper")
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
