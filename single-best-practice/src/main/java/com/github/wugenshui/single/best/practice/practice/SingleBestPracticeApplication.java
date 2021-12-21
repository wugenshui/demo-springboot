package com.github.wugenshui.single.best.practice.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author chenbo
 * @date 2020-12-23
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan("com.github.wugenshui.single.best.practice.**.mapper")
public class SingleBestPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleBestPracticeApplication.class, args);
    }

}
