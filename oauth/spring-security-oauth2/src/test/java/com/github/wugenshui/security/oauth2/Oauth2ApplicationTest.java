package com.github.wugenshui.security.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @author : chenbo
 * @date : 2021-12-29
 */
@SpringBootTest
class Oauth2ApplicationTest {
    @Test
    public void oauth2Test() {
        String response = new RestTemplate().postForObject("http://client:secret@localhost:8080/oauth/token", new Object(), String.class);
        System.out.println(response);
    }
}