package com.github.wugenshui.baseutil.baseutil.java.lang;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author : chenbo
 * @date : 2020-09-25
 */
@SpringBootTest
public class DoubleTest {

    /**
     * 浮点数运算因为存在精度问题，可能会出现预料之外的结果
     */
    @Test
    public void mathTest() {
        Double a = 0.7;
        Double b = 0.3;
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));

        System.out.println("(BigDecimal.valueOf(-1).compareTo(BigDecimal.ZERO) > 0) = " + (BigDecimal.valueOf(-1).compareTo(BigDecimal.ZERO) > 0));
        System.out.println("(BigDecimal.valueOf(0).compareTo(BigDecimal.ZERO) > 0) = " + (BigDecimal.valueOf(0).compareTo(BigDecimal.ZERO) > 0));
        System.out.println("(BigDecimal.valueOf(1).compareTo(BigDecimal.ZERO) > 0) = " + (BigDecimal.valueOf(1).compareTo(BigDecimal.ZERO) > 0));
    }
}
