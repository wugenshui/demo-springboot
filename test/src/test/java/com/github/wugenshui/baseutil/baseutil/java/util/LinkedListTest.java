package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
public class LinkedListTest {
    @Test
    public void apiTest() {
        LinkedList link = new LinkedList();
        link.add(3);
        link.addFirst(2);
        link.addFirst(1);
        link.addLast(4);
        link.addLast(5);
        System.out.println("link = " + link);
        System.out.println("link.size() = " + link.size());
        System.out.println("link.getFirst() = " + link.getFirst());
        System.out.println("link.getLast() = " + link.getLast());
    }

    @Test
    public void removeTest() {
        LinkedList link = new LinkedList();
        link.add(5);
        link.add(4);
        link.add(3);
        link.add(2);
        link.add(1);
        System.out.println("link = " + link);

        link.remove(4);
        System.out.println("link = " + link);

        // Integer 会调用 remove(Object o)
        Integer removeObj = 4;
        link.remove(removeObj);
        System.out.println("link = " + link);
    }
}
