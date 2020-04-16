package com.chenbo.daohikari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * APP启动类
 *
 * @author : chenbo
 * @date : 2019-12-22
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chenbo.daohikari.mapper")
public class DaoHikariApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoHikariApplication.class, args);
    }

}
