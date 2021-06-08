package com.github.wugenshui.baseutil.baseutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工具模块
 *
 * @author : chenbo
 * @date : 2019/12/07
 */
@SpringBootApplication
@Slf4j
public class BaseUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseUtilApplication.class, args);
        log.info("系统启动成功！");
    }

}
