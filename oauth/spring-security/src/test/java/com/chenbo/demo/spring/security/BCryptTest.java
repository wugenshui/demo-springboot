package com.chenbo.demo.spring.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BCryptTest {
    @Test
    public void EncodeTest() {
        String password = "admin";

        // 加密密码
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("加密后密码：" + hashpw);

        boolean checkpw = BCrypt.checkpw(password, hashpw);
        Assert.assertTrue(checkpw);

        hashpw = "$2a$10$vS.o5BJVfBJU47D7lGl/SukvAOnkJ2wDF8bMuUujfCmvjcWxr2uF6";
        checkpw = BCrypt.checkpw(password, hashpw);
        Assert.assertTrue(checkpw);
    }
}
