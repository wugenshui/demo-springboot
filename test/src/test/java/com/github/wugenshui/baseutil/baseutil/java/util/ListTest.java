package com.github.wugenshui.baseutil.baseutil.java.util;

import com.github.wugenshui.baseutil.baseutil.bean.StudentVO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : chenbo
 * @date : 2020-04-04
 */
@SpringBootTest
public class ListTest {

    @Test
    public void apiTest() {
        List list = null;
        if (list == null) {

        }
    }

    @Test
    public void foreachTest() {
        List<String> list = new ArrayList<>();

        // 可添加重复元素
        list.add("1");
        list.add("3");
        list.add("4");
        list.add("1");
        list.add("3");
        list.add("4");

        // 删除单条，从前面开始删除
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
     * for 与 foreach 耗时对比
     */
    @Test
    public void forTimeTest() {
        List<Integer> listNumber = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            listNumber.add(i);
        }

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("foreach 耗时");
        listNumber.forEach(i -> {

        });
        stopWatch.stop();

        stopWatch.start("for 耗时");
        for (int i = 0; i < listNumber.size(); i++) {

        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void eachTest2() {
        int listSize = 4;

        for (int i = 0; i < listSize; i++) {
            loop:
            for (int j = 0; j < listSize; j++) {
                for (int k = 0; k < listSize; k++) {
                    if (i + j + k == 3) {
                        continue loop;
                    }
                    System.out.println("" + i + j + k + " = " + (i + j + k));
                }
            }
        }
    }

    @Test
    public void emptyListTest() {
        // 空集合 遍历时会报NullPointerException,因此遍历前需要进去集合空判断
        List<String> nullList = null;

        //for (String s : nullList) {
        //    System.out.println("s = " + s);
        //}

        //nullList.forEach(l -> {
        //    System.out.println("l = " + l);
        //});

        //for (int i = 0; i < nullList.size(); i++) {
        //    System.out.println("nullList.get(i) = " + nullList.get(i));
        //}

        // emptyList则无影响
        List<String> emptyList = new ArrayList<>();

        for (String s : emptyList) {
            System.out.println("s = " + s);
        }

        emptyList.forEach(l -> {
            System.out.println("l = " + l);
        });

        for (int i = 0; i < emptyList.size(); i++) {
            System.out.println("emptyList.get(i) = " + emptyList.get(i));
        }
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
                getStudentDO("李一", 1L),
                getStudentDO("张三", 3L)
        );

        Map<Long, StudentVO> mapStudent = data.stream().collect(Collectors.toMap(StudentVO::getId, Function.identity()));
        System.out.println("mapStudent = " + mapStudent);


        Map<Long, String> mapStudent2 = data.stream().collect(Collectors.toMap(StudentVO::getId, m -> m.getAge() > 18 ? "成年" : "未成年"));
        System.out.println("mapStudent2 = " + mapStudent2);
    }

    private StudentVO getStudentDO(String name, Long id) {
        return StudentVO.builder()
                .id(id == null ? 1024L : id)
                .name(name)
                .age(18)
                .mobile("11122223333")
                .createTime(new Date())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Test
    public void genericsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        // 这里直接添加会报错
        // list.add("a");
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        // 但是通过反射添加，是可以的
        add.invoke(list, "kl");

        System.out.println(list);
    }
}
