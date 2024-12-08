package com.github.wugenshui.baseutil.baseutil.crypto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2024-09-14
 */
@SpringBootTest
public class CryptoUtilTest {

    @Test
    public void encrypt() {
        String encrypted = CryptoUtil.encrypt("second-password");
        System.out.println("加密后：" + encrypted);
        System.out.println("解密后：" + CryptoUtil.decrypt(encrypted));
    }
}