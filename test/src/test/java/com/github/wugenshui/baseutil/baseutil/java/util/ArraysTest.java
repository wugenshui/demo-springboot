package com.github.wugenshui.baseutil.baseutil.java.util;

import com.github.wugenshui.baseutil.baseutil.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class ArraysTest {

    /**
     * 数组内容输出，推荐使用 Arrays.deepToString
     */
    @Test
    public void toStringTest() {
        int[] arrayInt = {1, 2, 3, 4, 5};
        System.out.println(arrayInt);
        System.out.println(Arrays.toString(arrayInt));

        int[][] doubleArrayInt = {{1, 2}, {3, 4}, {5, 6, 7}};
        System.out.println(doubleArrayInt);
        System.out.println(Arrays.deepToString(doubleArrayInt));

        User[][] users = {{new User(1, "张三"), new User(1, "李四")}};
        System.out.println(Arrays.toString(users));
        System.out.println(Arrays.deepToString(users));
    }

}
