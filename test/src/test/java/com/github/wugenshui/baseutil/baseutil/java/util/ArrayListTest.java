package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ArrayListTest {
    @Test
    public void apiTest() {
        ArrayList arrayList = new ArrayList();
        System.out.println("arrayList.size() = " + arrayList.size());
        arrayList.add(1);
        System.out.println("arrayList.size() = " + arrayList.size());
        arrayList.add(2);
        System.out.println("arrayList.size() = " + arrayList.size());
        arrayList.remove(0);
        System.out.println("arrayList.size() = " + arrayList.size());

    }
}
