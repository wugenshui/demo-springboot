package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-07-13
 */
@SpringBootTest
public class StringTest {
    @Test
    public void SplitTest() {
        // 想要获取最后一段的字符串
        String name = "com.baidu.video";

        // split
        String[] splits = name.split("[.]");
        if (splits.length > 0) {
            System.out.println("last = " + splits[splits.length - 1]);
        }

        // substring
        int lastIndex = name.lastIndexOf(".");
        if (lastIndex > -1) {
            String last = name.substring(lastIndex + 1);
            System.out.println("last = " + last);
        }
    }

    @Test
    public void StringTest() {
        String a = "ab";
        String b = "ab";
        System.out.println(a == b);
        System.out.println(a.equals(b));

        a = new String("ab");
        b = new String("ab");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
