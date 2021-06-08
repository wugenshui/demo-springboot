package com.github.wugenshui.baseutil.baseutil.hutool;

import cn.hutool.crypto.SecureUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-09-23
 */
@SpringBootTest
public class SecureUtilTest {

    @Test
    public void md5Test() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "c4ca4238a0b923820dcc509a6f75849b");
        map.put("1q2w#E$R", "420f9d13cf0cde00d07bded973001ba2");
        map.put("abcd!@#$", "89a9caa3158431a0215a9598de3274f2");
        map.put("89a9caa3158431a0215a9598de3274f2", "d0fba36c3f6c7837a277958350cf3624");

        String actual = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // hutool 加密为32位小写形式
            actual = SecureUtil.md5(entry.getKey());
            Assert.assertEquals(entry.getValue(), actual);
            // spring
            actual = DigestUtils.md5DigestAsHex(entry.getKey().getBytes());
            Assert.assertEquals(entry.getValue(), actual);

            System.out.println("报错了");
        }
    }
}
