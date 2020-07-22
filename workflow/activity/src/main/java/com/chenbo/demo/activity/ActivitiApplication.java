package com.chenbo.demo.activity;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author : chenbo
 * @date : 2020-07-20
 */
@SpringBootApplication
public class ActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

    @Bean
    public Connector testConnector() {
        return integrationContext -> {
            System.out.println("以前叫代理，现在叫连接器被调用啦~~");
            return integrationContext;
        };
    }
}
