package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class MapTest {
    @Test
    public void Test() {
        Map<Integer, String> map = new HashMap<>();
        map.put(3, "张三");
        map.put(4, "李四");
        map.put(5, "王五");

        // 推荐使用，支持修改，但不支持添加和删除
        System.out.println("for (Map.Entry<Integer, String> entry : map.entrySet())");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("for (Integer i : map.keySet())");
        for (Integer i : map.keySet()) {
            System.out.println(i + " -> " + map.get(i));
        }

        System.out.println("map.forEach((key, value))");
        map.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });

        System.out.println("for (String value : map.values())");
        for (String value : map.values()) {
            System.out.println(value);
        }
    }
}
