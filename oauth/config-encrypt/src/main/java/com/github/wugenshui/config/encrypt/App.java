package com.github.wugenshui.config.encrypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 配置文件加密说明
 * 1. 引入 jasypt-spring-boot-starter
 * 2. 在配置文件中配置加密密钥
 * 3. 在配置文件中通过ENC()写入加密后配置
 *
 * @author : chenbo
 * @date : 2022-11-25
 */
@SpringBootApplication
@EnableEncryptableProperties
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(App.class, args);
        ConfigurableEnvironment environment = app.getEnvironment();
        System.out.println("jiami1:" + environment.getProperty("jiami1"));
        System.out.println("weijiami:" + environment.getProperty("weijiami"));
        System.out.println("jiami2:" + environment.getProperty("jiami2"));

        StringEncryptor encryptor = app.getBean(StringEncryptor.class);
        System.out.println("encryptor.encrypt(\"Ado@sthw123\") = " + encryptor.encrypt("Ado@sthw123"));
        System.out.println("encryptor.encrypt(\"1q2w#E$R\") = " + encryptor.encrypt("1q2w#E$R"));
        System.out.println("encryptor.encrypt(\"minio\") = " + encryptor.encrypt("minio"));
    }
}
