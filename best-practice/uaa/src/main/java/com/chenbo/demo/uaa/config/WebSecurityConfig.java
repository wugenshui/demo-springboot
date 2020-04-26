package com.chenbo.demo.uaa.config;

import com.chenbo.demo.uaa.service.JdbcUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : chenbo
 * @date : 2020-04-25
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 认证管理器，密码模式
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);

        //.inMemoryAuthentication()
        //// 在内存中创建用户并为密码加密
        //.withUser("user").password(passwordEncoder().encode("user")).roles("USER")
        //.and()
        //.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 安全拦截机制
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                //        //.antMatchers("/admin*").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
