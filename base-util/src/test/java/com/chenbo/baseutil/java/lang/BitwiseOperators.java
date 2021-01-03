package com.chenbo.baseutil.java.lang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 按位运算
 *
 * @author : chenbo
 * @date : 2021-01-03
 */
@SpringBootTest
public class BitwiseOperators {
    /**
     * & 两个位数都为1才为1，否则为0
     */
    @Test
    public void andTest() {
        System.out.println(Integer.toBinaryString(0b0011 & 0b0011));
        System.out.println(Integer.toBinaryString(0b0011 & 0b0010));
        System.out.println(Integer.toBinaryString(0b1111 & 0b1001));
    }
}
