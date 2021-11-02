package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : chenbo
 * @date : 2021-11-02
 */
@SpringBootTest
public class HashSetTest {
    @Test
    public void apiTest() {
        Set set = new HashSet<String>();
        set.add("张三");
        set.add("李四");
        set.add("张三");
        System.out.println("set = " + set);

    }
}
