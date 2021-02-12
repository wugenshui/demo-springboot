package com.chenbo.baseutil.java.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author : chenbo
 * @date : 2020-09-28
 */
@SpringBootTest
public class CloneTest {

    @Test
    public void ShallowCloneTest() {
        ShallowCloneObject source = new ShallowCloneObject();
        int[] ints = {1, 2, 3};
        source.setDeep(new Deep("张阿森纳"));
        source.setInts(ints);
        source.setAge(18);
        ShallowCloneObject target = (ShallowCloneObject) source.clone();
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertEquals(source.getAge(), target.getAge());
        Assert.assertEquals(source.getDeep(), target.getDeep());
        Assert.assertEquals(source.getInts(), target.getInts());

        int[] ints1 = target.getInts();
        ints1[0] = 5;
        Deep deep = target.getDeep();
        deep.setName("胡桃慧子");
        target.setAge(29);
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertNotEquals(source.getAge(), target.getAge());
        Assert.assertEquals(source.getDeep(), target.getDeep());
        Assert.assertEquals(source.getInts(), target.getInts());
    }

    @Getter
    @Setter
    @ToString
    public class ShallowCloneObject implements Cloneable {
        private Deep deep;
        private int age;
        private int[] ints;

        /**
         * 浅拷贝,对象引用
         */
        @Override
        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException("Clone Not Supported");
            }
        }
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public class Deep {
        private String name;
    }


    @Test
    public void DeepCloneTest() {
        DeepCloneObject source = new DeepCloneObject();
        int[] ints = {1, 2, 3};
        source.setDeep(new Deep("张阿森纳"));
        source.setInts(ints);
        source.setAge(18);
        DeepCloneObject target = (DeepCloneObject) source.clone();
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertEquals(source.getAge(), target.getAge());
        Assert.assertEquals(source.getDeep().toString(), target.getDeep().toString());
        Assert.assertEquals(Arrays.toString(source.getInts()), Arrays.toString(target.getInts()));

        int[] ints1 = target.getInts();
        ints1[0] = 5;
        Deep deep = target.getDeep();
        deep.setName("胡桃慧子");
        target.setAge(29);
        System.out.println("source = " + source + " 地址 = " + source.hashCode());
        System.out.println("target = " + target + " 地址 = " + target.hashCode());
        Assert.assertNotEquals(source.getAge(), target.getAge());
        Assert.assertNotEquals(source.getDeep(), target.getDeep());
        Assert.assertNotEquals(source.getInts(), target.getInts());
    }

    @Getter
    @Setter
    @ToString
    public class DeepCloneObject implements Cloneable {
        private Deep deep;
        private int age;
        private int[] ints;

        /**
         * 深拷贝,完全拷贝出一个新的对象
         */
        @Override
        protected Object clone() {
            DeepCloneObject person = new DeepCloneObject();
            int[] ints = new int[this.ints.length];
            System.arraycopy(this.ints, 0, ints, 0, ints.length);
            person.setAge(this.age);
            person.setInts(ints);
            person.setDeep(new Deep(this.deep.getName()));
            return person;
        }
    }

}
