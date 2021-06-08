package com.github.wugenshui.baseutil.baseutil.java.lang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类型转换测试
 *
 * @author : chenbo
 * @date : 2021-01-04
 */
@SpringBootTest
public class TypeParseTest {
    /**
     * 自动类型转换
     */
    @Test
    public void autoTypeParse() {
        byte b = Byte.MAX_VALUE;
        System.out.println("byte = " + b);

        short s = b;
        System.out.println("short = " + s);

        int i = s;
        System.out.println("int = " + i);

        long l = i;
        System.out.println("long = " + l);

        float f = l;
        System.out.println("float = " + f);

        double d = f;
        System.out.println("double = " + d);
    }

    /**
     * 强制类型转换
     */
    @Test
    public void forceTypeParse() {
        double d = Byte.MAX_VALUE;
        System.out.println("double = " + d);

        float f = (float) d;
        System.out.println("float = " + f);

        long l = (long) f;
        System.out.println("long = " + l);

        int i = (int) l;
        System.out.println("int = " + i);

        short s = (short) i;
        System.out.println("short = " + s);

        byte b = (byte) s;
        System.out.println("byte = " + b);

    }
}
