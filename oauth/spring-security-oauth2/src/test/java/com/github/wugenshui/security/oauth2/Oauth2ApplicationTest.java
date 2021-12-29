package com.github.wugenshui.security.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author : chenbo
 * @date : 2021-12-29
 */
@SpringBootTest
class Oauth2ApplicationTest {
    @Test
    public void oauth2Test() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String response = restTemplate.postForObject("http://client:secret@localhost:8080/oauth/token", null, String.class);
        System.out.println(response);
    }
}