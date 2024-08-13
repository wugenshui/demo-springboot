package com.wugenshui.github.ldap;

import com.wugenshui.github.ldap.util.LdapUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : chenbo
 * @date : 2024-07-09
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        LdapUtil ldapUtil = context.getBean(LdapUtil.class);
        // 测试认证方法
        System.out.println(ldapUtil.validLdapLogin("uname", "pwd?"));
    }
}
