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
     * & 对应两个位都为1才为1，否则为0
     */
    @Test
    public void andTest() {
        System.out.println(Integer.toBinaryString(0b11 & 0b11));
        System.out.println(Integer.toBinaryString(0b110 & 0b10));
        System.out.println(Integer.toBinaryString(0b1111 & 0b1001));
    }

    /**
     * | 对应两个位任意一位为1则为1
     */
    @Test
    public void orTest() {
        System.out.println(Integer.toBinaryString(0b11 | 0b11));
        System.out.println(Integer.toBinaryString(0b110 | 0b10));
        System.out.println(Integer.toBinaryString(0b1111 | 0b1001));
    }

    /**
     * ^ 同 | 类似，但有一点不同的是 如果两个操作位都为1的话，结果产生0
     */
    @Test
    public void caretTest() {
        System.out.println(Integer.toBinaryString(0b11 ^ 0b11));
        System.out.println(Integer.toBinaryString(0b110 ^ 0b10));
        System.out.println(Integer.toBinaryString(0b1111 ^ 0b1001));
    }

    /**
     * ~ 求反
     */
    @Test
    public void tildeTest() {
        System.out.println(Integer.toBinaryString(~0b11));
        System.out.println(Integer.toBinaryString(~0b110));
        System.out.println(Integer.toBinaryString(~0b1111));
    }
}
