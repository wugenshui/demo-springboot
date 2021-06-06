package com.chenbo.demo.best.oauth;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : chenbo
 * @date : 2020-04-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BCryptTest {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void encodeTest() {
        String password = "secret";

        // 加密密码
        String result1 = "$2a$10$VmNaOAWKo2Ob9NYhh0.kLef8Z5ca8uvW3v1BfDE41LAOwROgWRG7q";

        String result2 = bCryptPasswordEncoder.encode(password);
        System.out.println(password + "加密后密码：" + result2);

        String result3 = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(password + "加密后密码：" + result3);

        // 检查密码
        Assert.assertTrue(BCrypt.checkpw(password, result1));
        Assert.assertTrue(BCrypt.checkpw(password, result2));
        Assert.assertTrue(BCrypt.checkpw(password, result3));
    }
}
