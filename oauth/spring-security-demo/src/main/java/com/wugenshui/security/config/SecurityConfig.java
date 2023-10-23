package com.wugenshui.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    /**
     * 安全拦截配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                // 放行部分接口
                .antMatchers("/", "/home").permitAll()
                //.antMatchers("/f1").hasAuthority("f1")
                //.antMatchers("/f2").hasAuthority("f2")
                // 剩余接口都需要登录状态校验
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
        return httpSecurity.build();
    }

    /**
     * 密码加密器，会把客户端传来的密码进行加密，然后跟数据库中的密码做对比，要求数据库中的密码也是加密过的
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
