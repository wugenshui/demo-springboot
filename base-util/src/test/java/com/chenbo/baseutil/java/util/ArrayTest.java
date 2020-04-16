package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void forTest() {
        List<Integer> listNumber = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            listNumber.add(i);
        }

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("foreach耗时");
        listNumber.forEach(i -> {

        });
        stopWatch.stop();

        stopWatch.start("for耗时");
        for (int i = 0; i < listNumber.size(); i++) {

        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
