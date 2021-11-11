package com.github.wugenshui.baseutil.baseutil.algorithm;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * 检测四个点是否在正方形内部
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
    private int[][] datas;
    private int length = 0;

    public DetectSquares() {
        datas = new int[5000][2];
    }

    public void add(int[] point) {
        datas[length] = point;
        length++;
    }

    public int count(int[] point) {
        // System.out.println(Arrays.deepToString(datas));
        int counter = 0;
        int[] tempX = null;
        int[] tempY = null;
        int[] tempOther = null;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    // System.out.println(i + "," + j + "," + k);
                    if (point[0] != datas[i][0] && point[1] != datas[i][1]) {
                        tempOther = datas[i];
                        if (point[0] == datas[j][0] && point[1] == datas[k][1]) {
                            tempX = datas[j];
                            tempY = datas[k];
                        } else if (point[0] == datas[k][0] && point[1] == datas[j][1]) {
                            tempX = datas[k];
                            tempY = datas[j];
                        } else {
                            continue;
                        }
                    } else if (point[0] != datas[j][0] && point[1] != datas[j][1]) {
                        tempOther = datas[j];
                        if (point[0] == datas[i][0] && point[1] == datas[k][1]) {
                            tempX = datas[i];
                            tempY = datas[k];
                        } else if (point[0] == datas[k][0] && point[1] == datas[i][1]) {
                            tempX = datas[k];
                            tempY = datas[i];
                        } else {
                            continue;
                        }
                    } else if (point[0] != datas[k][0] && point[1] != datas[k][1]) {
                        tempOther = datas[k];
                        if (point[0] == datas[j][0] && point[1] == datas[i][1]) {
                            tempX = datas[j];
                            tempY = datas[i];
                        } else if (point[0] == datas[i][0] && point[1] == datas[j][1]) {
                            tempX = datas[i];
                            tempY = datas[j];
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    if (tempY[0] == tempOther[0] && tempX[1] == tempOther[1] && Math.abs(point[1] - tempX[1]) == Math.abs(point[0] - tempY[0])) {
                        System.out.println(i + "," + j + "," + k);
                        System.out.println(Arrays.toString(point) + "," + Arrays.toString(tempX) + "," + Arrays.toString(tempY) + "," + Arrays.toString(tempOther) + ",");
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
