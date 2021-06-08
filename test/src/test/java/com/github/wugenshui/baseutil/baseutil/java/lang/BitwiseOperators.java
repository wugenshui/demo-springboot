package com.github.wugenshui.baseutil.baseutil.java.lang;

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
     * & 按位或 只要有1出现结果就是1，否则为0
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
     * ^ 按位异或 不同为1，相同为0
     */
    @Test
    public void caretTest() {
        System.out.println(Integer.toBinaryString(0b11 ^ 0b11));
        System.out.println(Integer.toBinaryString(0b110 ^ 0b10));
        System.out.println(Integer.toBinaryString(0b1111 ^ 0b1001));
    }

    /**
     * ~ 按位取反 0变1，1变0
     */
    @Test
    public void tildeTest() {
        System.out.println(Integer.toBinaryString(~0b11));
        System.out.println(Integer.toBinaryString(~0b110));
        System.out.println(Integer.toBinaryString(~0b1111));
    }

    /**
     * << 左移运算符
     */
    @Test
    public void leftTest() {
        System.out.println(Integer.toBinaryString(0b11111111 << 2));
        System.out.println(Integer.toBinaryString(0b00000000 << 2));
        System.out.println(Integer.toBinaryString(0b11001111 << 2));
        System.out.println(Integer.toBinaryString(0b11110011 << 2));
    }

    /**
     * >> 右移运算符
     */
    @Test
    public void rightTest() {
        System.out.println(Integer.toBinaryString(0b11 >> 2));
        System.out.println(Integer.toBinaryString(0b110 >> 2));
        System.out.println(Integer.toBinaryString(0b1111 >> 2));
    }
}
