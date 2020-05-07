package com.chenbo.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class ListTest {
    @Test
    public void foreachTest() {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.remove("4");
        list.remove("3");

        System.out.println("for (Iterator iterator = list.iterator(); iterator.hasNext(); )");
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            String i = (String) iterator.next();
            System.out.println(i);
        }

        System.out.println("Iterator iterator = list.iterator()");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            System.out.println(str);
        }

        System.out.println("for (String str : list)");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("for (int i = 0; i < list.size(); i++)");
        for (int i = 0; i < list.size(); i++) {
            list.set(i, "change" + list.get(i));
            System.out.println(list.get(i));
        }

        System.out.println("list.forEach(s -> System.out.println(s))");
        list.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("list.forEach(System.out::println)");
        list.forEach(System.out::println);

        System.out.println("list");
        System.out.println(list);
    }
}
