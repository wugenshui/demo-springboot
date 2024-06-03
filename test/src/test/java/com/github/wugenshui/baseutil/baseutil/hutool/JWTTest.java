package com.github.wugenshui.baseutil.baseutil.hutool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * JWT
 * Header 头部信息，主要声明了JWT的签名算法等信息
 * Payload 载荷信息，主要承载了各种声明并传递明文数据
 * Signature 签名，拥有该部分的JWT被称为JWS，也就是签了名的JWS，用于校验数据
 *
 * @author : chenbo
 * @date : 2022-08-01
 */
@SpringBootTest
public class JWTTest {
    @Test
    public void jwtTest() {
        // 密钥
        byte[] secretKey = "1234567890".getBytes();

        String token = JWT.create()
                // 过期时间
                .setExpiresAt(DateUtil.tomorrow())
                // 用户id
                .setPayload("userId", "1")
                // 应用id
                .setPayload("appId", "123456")
                // 调用系统名称
                .setPayload("system", "大模型应用开发及运行平台")
                .setKey(secretKey)
                .sign();
        System.out.println("token = " + token);

        boolean verify = JWT.of(token).setKey(secretKey).validate(0);
        Assert.assertTrue(verify);

        boolean verifyFalse = JWT.of(token).setKey("1".getBytes()).validate(0);
        Assert.assertFalse(verifyFalse);

        JWT jwt = JWT.of(token);
        // JWT
        System.out.println(jwt.getHeader(JWTHeader.TYPE));
        // HS256
        System.out.println(jwt.getHeader(JWTHeader.ALGORITHM));
        System.out.println(jwt.getPayloads());
        // 1234567890
        System.out.println(jwt.getPayload("sub"));
        // looly
        System.out.println(jwt.getPayload("name"));
        // true
        System.out.println(jwt.getPayload("admin"));
    }
}
