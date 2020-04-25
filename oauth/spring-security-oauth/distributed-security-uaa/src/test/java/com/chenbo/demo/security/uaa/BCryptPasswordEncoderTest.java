package com.chenbo.demo.security.uaa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : chenbo
 * @date : 2020-04-22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BCryptPasswordEncoderTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        System.out.println(passwordEncoder.encode("secret"));
    }
}
