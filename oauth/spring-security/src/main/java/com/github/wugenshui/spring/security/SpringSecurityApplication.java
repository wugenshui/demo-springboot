package com.github.wugenshui.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 权限框架说明
 * 1. 引入 spring-boot-starter-security
 * 2. 配置用户、权限
 * 3. 增加权限判断标记进行请求自动拦截 @PreAuthorize("hasAuthority('f1')")
 *
 * @author : chenbo
 * @date : 2020-04-19
 */
@SpringBootApplication
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
