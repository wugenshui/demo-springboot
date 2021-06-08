package com.github.wugenshui.baseutil.baseutil.java.lang;

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
    public void EqualsTest() {
        String a = "ab";
        String b = "ab";
        System.out.println(a == b);
        System.out.println(a.equals(b));

        a = new String("ab");
        b = new String("ab");
        System.out.println(a == b);
        System.out.println(a.equals(b));

        a = "张三";
        b = "张三";
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    @Test
    public void replaceTest() {
        String target = "340826199205725324";
        String result = target.substring(0, 6) + "**********" + target.substring(16, 18);
        System.out.println("result = " + result);
    }
}
