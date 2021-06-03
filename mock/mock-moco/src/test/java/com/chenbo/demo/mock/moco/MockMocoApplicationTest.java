package com.chenbo.demo.mock.moco;

import com.github.dreamhead.moco.HttpServer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.running;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MockMocoApplicationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void listTest() throws Exception {
        HttpServer server = httpServer(12306);
        server.response("foo");

        running(server, () -> {
            try {
                String response = restTemplate.getForObject("http://localhost:12306", String.class);
                Assert.assertEquals("foo", response);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        });
    }

}