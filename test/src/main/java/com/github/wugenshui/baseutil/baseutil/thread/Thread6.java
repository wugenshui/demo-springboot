package com.github.wugenshui.baseutil.baseutil.thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * B线程中断不会影响到A
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread6 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("foo");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread6 listAdd = new Thread6();

        new Thread(new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    listAdd.add();
                    System.out.println("当前线程" + Thread.currentThread().getName() + "添加了一个元素");
                    Thread.sleep(500);
                }
            }
        })).start();

        new Thread(new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    if (listAdd.size() == 5) {
                        System.out.println("当前线程" + Thread.currentThread().getName() + "收到通知，线程停止");
                        throw new RuntimeException();
                    }
                }
            }
        })).start();
    }
}
