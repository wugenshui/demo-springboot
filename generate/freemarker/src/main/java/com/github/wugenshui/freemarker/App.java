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
        property.setName("test-service".replace("_", "-"));
        property.setNamespace("com.huaweisoft");
        property.setRepo("http://gitlab/tool/cicd_doc");
        property.setRegistry("docker.io");
        property.setCompany("wu");

        // bean.generateJava(property);
        bean.generateVuePress(property);
    }
}
