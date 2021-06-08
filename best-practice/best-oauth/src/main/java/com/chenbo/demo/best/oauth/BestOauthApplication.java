package com.github.wugenshui.best.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : chenbo
 * @date : 2020-04-25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BestOauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(BestOauthApplication.class, args);
    }
}
