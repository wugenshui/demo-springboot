package com.chenbo.daomybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenbo.daomybatisplus.**.mapper")
/**
 * App启动类
 * @author chenbo
 * @since 2019-12-01
 */
public class DaoMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoMybatisPlusApplication.class, args);
    }

}
