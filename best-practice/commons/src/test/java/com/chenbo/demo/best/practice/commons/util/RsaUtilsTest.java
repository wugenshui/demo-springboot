package com.chenbo.demo.best.practice.commons.util;

import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * @author : chenbo
 * @date : 2020-05-02
 */
public class RsaUtilsTest {

    private String privateFilePath = "E://id_rsa";
    private String publicFilePath = "E://id_rsa.pub";

    @Test
    public void generateKey() throws Exception {
        // 生成密钥对
        RsaUtils.generateKey(publicFilePath, privateFilePath, "hello", 2048);

        // 获取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateFilePath);
        System.out.println("privateKey = " + privateKey);
        // 获取公钥
        PublicKey publicKey = RsaUtils.getPublicKey(publicFilePath);
        System.out.println("publicKey = " + publicKey);
    }
}