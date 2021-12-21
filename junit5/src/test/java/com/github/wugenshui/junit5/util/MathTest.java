package com.github.wugenshui.junit5.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author : chenbo
 * @date : 2021-12-21
 */
class MathTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add(Integer numA) {
        System.out.println(numA);
    }
}