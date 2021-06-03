package com.chenbo.demo.mock.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {
    private RestTemplate restTemplate;

    @Test
    public void test() {
        RestTemplate mockTemplate = mock(RestTemplate.class);
        when(mockTemplate.getForObject("/other", String.class)).thenReturn("其它业务系统返回值");

        System.out.println(mockTemplate.getForObject("/other", String.class));
    }
}
