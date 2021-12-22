package com.github.wugenshui.junit5.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author : chenbo
 * @date : 2021-12-21
 */
class MathTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void paramTest(Integer numA) {
        Assertions.assertTrue(numA > 0);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "-4,-3,-7", "0,0,0"})
    void addTest(ArgumentsAccessor args) {
        int result = Math.add(args.getInteger(0), args.getInteger(1));
        Assertions.assertEquals(args.getInteger(2), result);
    }
}