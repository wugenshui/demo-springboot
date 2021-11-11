package com.github.wugenshui.baseutil.baseutil.algorithm;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 检测四个点是否组成正方形
 *
 * @author : chenbo
 * @date : 2021-11-11
 */
@SpringBootTest
public class CheckSquare {
    @Test
    public void apiTest() {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{5, 10});
        detectSquares.add(new int[]{10, 5});
        detectSquares.add(new int[]{10, 10});
        Assert.assertEquals(1, detectSquares.count(new int[]{5, 5}));  // 返回 1 。你可以选择：
        //   - 第一个，第二个，和第三个点
        detectSquares.add(new int[]{3, 0});
        detectSquares.add(new int[]{8, 0});
        detectSquares.add(new int[]{8, 5});
        Assert.assertEquals(1, detectSquares.count(new int[]{3, 5}));  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。

        detectSquares.add(new int[]{9, 0});
        detectSquares.add(new int[]{9, 8});
        detectSquares.add(new int[]{1, 8});
        Assert.assertEquals(1, detectSquares.count(new int[]{1, 0}));
        detectSquares.add(new int[]{0, 0});
        detectSquares.add(new int[]{8, 0});
        detectSquares.add(new int[]{8, 8});
        Assert.assertEquals(2, detectSquares.count(new int[]{0, 8}));
    }
}

class DetectSquares {
    private Map<Integer, Integer> map;

    public DetectSquares() {
        map = new HashMap<>();
    }

    public void add(int[] point) {
        int key = (point[0] << 10) + point[1];
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public int count(int[] point) {
        int counter = 0;
        int x = point[0];
        int y = point[1];
        for (Integer key : map.keySet()) {
            int countOther = map.get(key);
            Integer otherX = key >> 10;
            Integer otherY = key - (otherX << 10);
            // System.out.println(otherX + "," + otherY);
            // 两点坐标不同说明为对角线的点，如果两边相减大小相同说明为正方形
            int a = Math.abs(x - otherX);
            int b = Math.abs(y - otherY);
            // 先检验边长,不同或者为0则中断本轮次
            if (a != b || a == 0 || b == 0) {
                continue;
            }
            int key1 = (x << 10) + otherY;
            int key2 = (otherX << 10) + y;
            if (map.containsKey(key1) && map.containsKey(key2)) {
                counter += map.get(key1) * map.get(key2) * countOther;
            }

        }

        return counter;
    }
}
