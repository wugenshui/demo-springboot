package com.chenbo.baseutil.java.util;

import com.chenbo.baseutil.bean.StudentVO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     * ArrayList 之类的才可以调用
     */
    @Test
    public void removeAllTest() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        ArrayList removeList = new ArrayList();
        removeList.add(2);
        boolean deleted = list.removeAll(removeList);
        System.out.println("deleted = " + deleted);
        System.out.println("list = " + list);
    }

    @Test
    public void list2MapTest() {
        List<StudentVO> data = Arrays.asList(
                new StudentVO(1L, "李一", 17, "12136"),
                new StudentVO(3L, "张三", 22, "12137")
        );

        Map<Long, StudentVO> mapStudent = data.stream().collect(Collectors.toMap(StudentVO::getId, Function.identity()));
        System.out.println("mapStudent = " + mapStudent);
    }
}
