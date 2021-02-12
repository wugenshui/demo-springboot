package com.chenbo.baseutil;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2021-01-03
 */
@SpringBootTest
public class MyTest {
    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(0x11 ^ 0x11));
        System.out.println(Integer.toBinaryString(0x11 ^ 0x10));
    }
}
