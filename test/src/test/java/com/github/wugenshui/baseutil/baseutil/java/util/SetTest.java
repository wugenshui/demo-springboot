package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

/**
 * @author : chenbo
 * @date : 2021-06-09
 */
@SpringBootTest
public class SetTest {
    /**
     * HashSet 可以添加重复元素，但是只会存储一份
     */
    @Test
    public void setTest() {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(1);
        System.out.println("list = " + hashSet);
    }
}
