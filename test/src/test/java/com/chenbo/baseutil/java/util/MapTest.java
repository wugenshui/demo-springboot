package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class MapTest {
    @Test
    public void HashMapTest() {
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

    @Test(expected = NullPointerException.class)
    public void ConTest() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
        // 初始化
        map.put("key", "oldValue");

        // 覆盖原有值
        String put = map.put("key", "newValue");
        System.out.println(put);
        System.out.println(map);

        // 如果有值则不修改，如果没有存入设置值
        put = map.putIfAbsent("key", "newValue2");
        System.out.println(put);
        System.out.println(map);

        // key或value为null会抛出空指针异常
        put = map.put("key", null);
        System.out.println(put);
        System.out.println(map);
    }
}
