package com.github.wugenshui.config.encrypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author : chenbo
 * @date : 2022-11-25
 */
@SpringBootApplication
@EnableEncryptableProperties
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(App.class, args);
        ConfigurableEnvironment environment = app.getEnvironment();
        System.out.println("aa:" + environment.getProperty("aa"));
        System.out.println("ea:" + environment.getProperty("ea"));
        System.out.println("eb:" + environment.getProperty("eb"));

        StringEncryptor encryptor = app.getBean(StringEncryptor.class);
        System.out.println("encryptor.encrypt(\"ruphie\") = " + encryptor.encrypt("ruphie"));
        System.out.println("encryptor.encrypt(\"minio\") = " + encryptor.encrypt("minio"));
    }
}
