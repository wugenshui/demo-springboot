package com.chenbo.baseutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工具模块
 *
 * @author : chenbo
 * @date : 2019/12/07
 */
@SpringBootApplication
public class BaseUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseUtilApplication.class, args);
        System.out.println(1);
    }

}
