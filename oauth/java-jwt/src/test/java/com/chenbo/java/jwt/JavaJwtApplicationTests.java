package com.chenbo.java.jwt;

import com.alibaba.fastjson.JSONObject;
import com.chenbo.java.jwt.entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JavaJwtApplicationTests {

    @Test(expected = HttpServerErrorException.class)
    @Ignore
    public void noOauthTest() {
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject("http://localhost:8080/api/getMessage", Object.class);
    }

    @Test
    @Ignore
    public void loginTest() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("1", "zhangsan", "123");
        ResponseEntity<JSONObject> obj = restTemplate.postForEntity("http://localhost:8080/api/login", user, JSONObject.class);

        System.out.println("obj = " + obj);
        JSONObject json = obj.getBody();
        assert json != null;
        String token = json.getString("token");
        System.out.println("token = " + token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        ResponseEntity<String> responseGetMessage = restTemplate.exchange("http://localhost:8080/api/getMessage", HttpMethod.GET, new HttpEntity<String>(null, headers), String.class);
        System.out.println(responseGetMessage.getBody());
    }

}
