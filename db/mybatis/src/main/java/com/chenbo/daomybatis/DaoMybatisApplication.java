package com.chenbo.daomybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@MapperScan("com.chenbo.daomybatis.mapper")
@SpringBootApplication
public class DaoMybatisApplication {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DaoMybatisApplication.class, args);
    }

}
