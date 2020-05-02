package com.chenbo.demo.admin.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author : chenbo
 * @date : 2020-04-27
 */
@Configuration
public class TokenConfig {
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("key/jwt.pub");
        try {
            String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
            // 公钥解密
            converter.setVerifierKey(publicKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
