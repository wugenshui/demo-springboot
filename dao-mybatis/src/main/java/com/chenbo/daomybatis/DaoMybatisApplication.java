package com.chenbo.daomybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.chenbo.daomybatis.mapper")
@SpringBootApplication
public class DaoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoMybatisApplication.class, args);
    }

}
