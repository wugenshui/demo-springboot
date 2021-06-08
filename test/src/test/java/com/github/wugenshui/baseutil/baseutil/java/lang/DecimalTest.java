package com.github.wugenshui.baseutil.baseutil.java.lang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author : chenbo
 * @date : 2020-09-25
 */
@SpringBootTest
public class DecimalTest {

    @Test
    public void mathTest() {
        BigDecimal a = BigDecimal.valueOf(0.7);
        BigDecimal b = BigDecimal.valueOf(0.3);
        System.out.println("a.add(b) = " + a.add(b));
        System.out.println("a.subtract(b) = " + a.subtract(b));
        System.out.println("a.multiply(b) = " + a.multiply(b));
        System.out.println("a.divide(b) = " + a.divide(b, 3));


    }
}
