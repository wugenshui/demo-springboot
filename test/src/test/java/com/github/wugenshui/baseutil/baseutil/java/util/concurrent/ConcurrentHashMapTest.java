package com.github.wugenshui.baseutil.baseutil.java.util.concurrent;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 内部使用段（Segment）来表示这些不同的部分，每个段其实就是一个小的HashTable，它们都有自己的锁
 * 减小锁的粒度从而降低锁竞争
 * 最高支持16个线程的并发修改操作
 *
 * @author : chenbo
 * @date : 2021-02-14
 */
@SpringBootTest
public class ConcurrentHashMapTest {

    @Test
    public void test() {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("a", "k1");
        concurrentHashMap.put("b", "k2");
        concurrentHashMap.put("c", "k3");
        concurrentHashMap.putIfAbsent("d", "k4");

        for (Map.Entry<String, Object> entry : concurrentHashMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }
}
