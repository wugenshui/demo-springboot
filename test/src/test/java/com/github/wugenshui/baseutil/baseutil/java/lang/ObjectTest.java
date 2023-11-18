package com.github.wugenshui.baseutil.baseutil.java.lang;

import org.junit.jupiter.api.Test;

/**
 * @author : chenbo
 * @date : 2023-11-18
 */
public class ObjectTest {
    @Test
    void ObjectDotTest() {
        String str = String.format("Hello %s AND %s", "World", "Java");
        System.out.println(str);

        String[] args = {"World", "Java"};
        String str2 = String.format("Hello %s AND %s", args);
        System.out.println(str2);
    }
}
