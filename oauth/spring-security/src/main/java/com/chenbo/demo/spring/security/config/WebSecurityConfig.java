package com.chenbo.demo.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "home").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll();
    }

    /**
     * 安全拦截配置
     *
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    // 密码解码器
                    //.passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN").and()
                    .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
