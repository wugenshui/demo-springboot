package com.chenbo.baseutil.jdk.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArrayTest {

    @Test
    public void toStringTest() {
        int[] arrayInt = new int[]{1, 2, 3, 4, 5};
        System.out.println(arrayInt);
        System.out.println(Arrays.toString(arrayInt));

        int[][] doubleArrayInt = new int[][]{{1, 2}, {3, 4}, {5, 6, 7}};
        System.out.println(doubleArrayInt);
        System.out.println(Arrays.deepToString(doubleArrayInt));

    }
}
