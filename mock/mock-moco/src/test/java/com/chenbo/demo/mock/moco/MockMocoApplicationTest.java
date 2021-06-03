package com.chenbo.demo.mock.moco;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MockMocoApplicationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void listTest() {
        String response = restTemplate.getForObject("http://localhost:12306", String.class);
        Assert.assertEquals("foo", response);
    }

}