package com.chenbo.baseutil.java.lang;

import org.junit.Test;

/**
 * @author : chenbo
 * @date : 2020-09-14
 */
public class IntegerTest {

    @Test
    public void EqualsTest() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        a = 128;
        b = 128;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        int i = 1;
        float f = 1.1F;
        double d = 1.1;
        double v = 1 / f + d;
        System.out.println("v = " + v);
    }
}
