package com.github.wugenshui.baseutil.baseutil.crypto;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 加密解密工具类
 *
 * @author : chenbo
 * @date : 2024-09-14
 */
@Slf4j
public class CryptoUtil {
    /**
     * 加密方法
     */
    public static final String CIPHER_METHOD = "AES";
    /**
     * 加密模式
     */
    public static final String CIPHER_MODE = "AES/ECB/PKCS5Padding";
    /**
     * 默认加密密钥，按需替换，加解密需一致
     */
    private static final String DEFAULT_KEY = "1q2w3e4r%T^Y&U*I";

    private CryptoUtil() {
        throw new RuntimeException("工具类无法实例化");
    }

    /**
     * 字符串对称加密方法，这里使用 AES ECB 加密
     *
     * @param text 加密前文本
     * @return 加密后密文
     */
    public static String encrypt(String text) {
        try {
            byte[] content = text.getBytes(StandardCharsets.UTF_8);
            byte[] keyByte = DEFAULT_KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, CIPHER_METHOD);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] data = cipher.doFinal(content);
            final Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(data);
        } catch (Exception e) {
            log.error("CryptoUtil.encrypt error", e);
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * 字符串解密方法，这里使用 AES ECB 解密
     *
     * @param messageBase64 加密后文本
     * @return 加密前文本
     */
    public static String decrypt(String messageBase64) {
        try {
            final Base64.Decoder decoder = Base64.getDecoder();
            byte[] messageByte = decoder.decode(messageBase64);
            byte[] keyByte = DEFAULT_KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, CIPHER_METHOD);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] content = cipher.doFinal(messageByte);
            return new String(content, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("CryptoUtil.decrypt error", e);
            throw new RuntimeException(e.toString());
        }
    }
}
