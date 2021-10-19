package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class MapTest {
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
