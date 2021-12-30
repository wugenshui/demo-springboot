package com.github.wugenshui.security.oauth2;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 先运行服务，然后执行单元测试
 *
 * @author : chenbo
 * @date : 2021-12-29
 */
class Oauth2ApplicationTest {
    @Test
    public void clientCredentialsModeTest() {
        // basic身份认证
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("client", "secret").build();
        // 请求参数
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("scope", "any");
        // 发起请求
        JsonNode response = restTemplate.postForObject("http://localhost:8080/oauth/token", params, JsonNode.class);
        System.out.println(response);
    }

    @Test
    void passwordModeTest() {
        // basic身份认证
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("client", "secret").build();
        // 请求参数
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "admin");
        params.add("password", "123456");
        params.add("scope", "any");
        // 发起请求
        JsonNode response = restTemplate.postForObject("http://localhost:8080/oauth/token", params, JsonNode.class);
        System.out.println(response);
    }

    @Test
    public void passwordEncodeTest() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        for (int i = 0; i < 1; i++) {
            String encode = passwordEncoder.encode("123");
            Assertions.assertTrue(passwordEncoder.matches("123", encode));
            System.out.println(encode);
        }
        Assertions.assertTrue(passwordEncoder.matches("123", "{noop}123"));
    }
}