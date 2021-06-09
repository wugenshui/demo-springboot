package com.github.wugenshui.db.flyway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.wugenshui.db.flyway.mapper")
public class DbFlywayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbFlywayApplication.class, args);
    }
}
