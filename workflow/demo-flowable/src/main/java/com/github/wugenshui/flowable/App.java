package com.github.wugenshui.flowable;

import com.github.wugenshui.flowable.demo.QingJiaTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : chenbo
 * @date : 2024-12-08
 */
@SpringBootApplication(proxyBeanMethods = false)
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        QingJiaTest bean = run.getBean(QingJiaTest.class);
        bean.run();
    }
}
