package com.github.wugenshui.sa.token;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : chenbo
 * @date : 2023-07-14
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }
}
