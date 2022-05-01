package com.github.wugenshui.freemarker;

import com.github.wugenshui.freemarker.model.JavaProperty;
import com.github.wugenshui.freemarker.service.GenerateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : chenbo
 * @date : 2022-04-28
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        GenerateService bean = run.getBean(GenerateService.class);
        JavaProperty property = new JavaProperty();
        property.setName("testservice");
        property.setNamespace("com.huaweisoft");
        bean.generateJava(property);
    }
}
