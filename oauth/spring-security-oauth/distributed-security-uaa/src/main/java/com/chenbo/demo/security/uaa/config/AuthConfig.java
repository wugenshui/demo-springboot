package com.chenbo.demo.security.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author : chenbo
 * @date : 2020-04-21
 */
@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 密码加密工具
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder().encode("secret"))
                .authorizedGrantTypes("authorization_code")
                .scopes("all")
                .redirectUris("https://wwww.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 放行校验令牌接口
        security.checkTokenAccess("permitAll()");
    }
}
