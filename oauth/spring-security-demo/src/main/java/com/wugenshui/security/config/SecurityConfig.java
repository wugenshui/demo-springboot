package com.wugenshui.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                // 放行部分接口
                .antMatchers("/", "/home").permitAll()
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
