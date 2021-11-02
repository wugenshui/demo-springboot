package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2021-11-02
 */
@SpringBootTest
public class LinkedHashMapTest {
    @Test
    public void apiTest() {
        LinkedHashMap map = new LinkedHashMap<>();
        map.put(1, 2);
        map.put(2, 4);
        map.put(3, 9);
        map.put(4, 16);
        System.out.println(map);
    }
}
