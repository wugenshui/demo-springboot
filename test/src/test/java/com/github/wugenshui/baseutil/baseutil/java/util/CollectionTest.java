package com.github.wugenshui.baseutil.baseutil.java.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : chenbo
 * @date : 2023-11-18
 */
public class CollectionTest {

    @Data
    class Student {
        Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private Integer age;
    }

    @Test
    void steamTest() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 11));
        students.add(new Student("李四", 12));
        students.add(new Student("王五", 11));

        // 按年龄分组
        students.stream().map(Student::getAge).distinct().forEach(System.out::println);
        Map<Integer, List<Student>> ageStudentMap = students.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println("ageStudentMap = " + ageStudentMap);
    }
}
