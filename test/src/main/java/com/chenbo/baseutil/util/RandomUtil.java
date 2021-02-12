package com.chenbo.baseutil.util;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class RandomUtil {
    public static int getRandomNumber(int min, int max) {
        return new Double((Math.random() * (max - min)) + min).intValue();
    }
}
