package com.chenbo.baseutil.jdk.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ListTest {
    @Test
    public void foreachTest() {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

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
            System.out.println(list.get(i));
        }

        System.out.println("list.forEach(System.out::println)");
        list.forEach(System.out::println);
    }
}
