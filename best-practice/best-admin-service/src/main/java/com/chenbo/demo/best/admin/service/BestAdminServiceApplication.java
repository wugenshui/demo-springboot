package com.github.wugenshui.best.admin.service;

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
@MapperScan("com.github.wugenshui.best.admin.service.mapper")
public class BestAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestAdminServiceApplication.class, args);
    }

}
