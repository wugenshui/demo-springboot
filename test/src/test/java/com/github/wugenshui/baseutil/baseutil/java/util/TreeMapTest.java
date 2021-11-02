package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TreeMap;

/**
 * @author : chenbo
 * @date : 2021-11-02
 */
@SpringBootTest
public class TreeMapTest {
    @Test
    public void apiTest() {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        tree.put(1, 1);
        tree.put(3, 9);
        tree.put(2, 4);
        tree.put(4, 16);
        System.out.println(tree);
    }
}
