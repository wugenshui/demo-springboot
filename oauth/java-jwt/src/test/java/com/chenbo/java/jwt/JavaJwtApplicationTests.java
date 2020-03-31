package com.chenbo.java.jwt;

import com.chenbo.java.jwt.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JavaJwtApplicationTests {

    @Test(expected = ResourceAccessException.class)
    public void noOauthTest() {
        RestTemplate restTemplate = new RestTemplate();
        Object obj = restTemplate.getForObject("http://localhost:8080/api/getMessage", Object.class);

        //Object obj = restTemplate.getForObject("http://www.baidu.com", Object.class);
        System.out.println("obj = " + obj);
    }

    @Test
    public void loginTest() {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("1", "zhangsan", "123");
        Object obj = restTemplate.postForEntity("http://localhost:8080/api/login", user, Object.class);

        //Object obj = restTemplate.getForObject("http://www.baidu.com", Object.class);
        System.out.println("obj = " + obj);
    }

}
