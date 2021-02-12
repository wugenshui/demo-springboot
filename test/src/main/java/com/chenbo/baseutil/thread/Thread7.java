package com.chenbo.baseutil.thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait和notify的时候，需要配置 synchronized 一起使用
 * wait释放锁，notify不释放锁
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread7 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("foo");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread7 listAdd = new Thread7();

        final Object lock = new Object();

        Thread t1 = new Thread(new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        listAdd.add();
                        System.out.println("当前线程" + Thread.currentThread().getName() + "添加了一个元素");
                        Thread.sleep(500);
                        if (listAdd.size() == 5) {
                            System.out.println("发出唤醒通知");
                            lock.notify();
                        }
                    }
                }
            }
        }));

        Thread t2 = new Thread(new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    if (listAdd.size() != 5) {
                        lock.wait();
                    }
                    System.out.println("当前线程" + Thread.currentThread().getName() + "收到通知，线程停止");
                    throw new RuntimeException();
                }
            }
        }));

        t2.start();
        t1.start();
    }
}
