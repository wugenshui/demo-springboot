package com.github.wugenshui.baseutil.baseutil.java.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class HashMapTest {
    @Test
    public void apiTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(3, "张三");
        map.put(4, "李四");
        map.put(4, "李四");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(5));
    }

    public class Order {
        String date;
        private Order(String dateStr) {
            this.date = dateStr;
        }
        String getTransactionDay() {
            return date;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "date='" + date + '\'' +
                    '}';
        }
    }

    public class Type {

        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeString() {
            switch (type) {
                case 0:
                    return "ZERO";
                case 1:
                    return "ONE";
                default:
                    return "OTHER";
            }
        }
    }

    @Test
    public void filter() {
        // Type type = new Type();
        // type.setType(0);
        // System.out.println("type.getTypeString() = " + type.getTypeString());

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("2021-10-26"));
        orders.add(new Order("2021-10-25"));
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String currentDay = formatter.format(new Date());
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (!currentDay.equals(order.getTransactionDay())) {
                iterator.remove();
            }
        }
        System.out.println("orders = " +  Arrays.toString(orders.toArray()));
    }
        // 返回大于等于initialCapacity的最小的二次幂数值。
        // >>> 操作符表示无符号右移，高位取0。
        // | 按位或运算
        public int tableSizeFor ( int cap){
            int n = cap - 1;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            return (n < 0) ? 1 : (n >= 100) ? 100 : n + 1;
        }

        @Test
        public void forTest () {
            Map<Integer, String> map = new HashMap<>();
            map.put(3, "张三");
            map.put(4, "李四");
            map.put(5, "王五");

            // 推荐使用，支持修改，但不支持添加和删除
            System.out.println("for (Map.Entry<Integer, String> entry : map.entrySet())");
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

            System.out.println("for (Integer i : map.keySet())");
            for (Integer i : map.keySet()) {
                System.out.println(i + " -> " + map.get(i));
            }

            System.out.println("map.forEach((key, value))");
            map.forEach((key, value) -> {
                System.out.println(key + " -> " + value);
            });

            System.out.println("for (String value : map.values())");
            for (String value : map.values()) {
                System.out.println(value);
            }
        }
    }
