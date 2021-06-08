package com.github.wugenshui.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全拦截配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "home").permitAll()
                //.antMatchers("/f1").hasAuthority("f1")
                //.antMatchers("/f2").hasAuthority("f2")
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll();
    }

    /**
     * 密码解码器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     *
     * @param auth
     */
    //@Override
    //public void configure(AuthenticationManagerBuilder auth) {
    //    try {
    //        auth.inMemoryAuthentication()
    //                // 密码解码器
    //                //.passwordEncoder(NoOpPasswordEncoder.getInstance())
    //                .passwordEncoder(new BCryptPasswordEncoder())
    //                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN").and()
    //                .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER");
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}
}
