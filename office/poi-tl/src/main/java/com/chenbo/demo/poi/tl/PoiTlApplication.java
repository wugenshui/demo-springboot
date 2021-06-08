package com.github.wugenshui.poi.tl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.wugenshui.poi.tl.mapper")
public class PoiTlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiTlApplication.class, args);
    }

}
