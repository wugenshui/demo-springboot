package com.chenbo.baseutil.thread;

/**
 * 线程安全的概念：当多个线程访问某一个类（对象或方法）时，这个类始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread1 extends Thread {

    private int count = 100;

    public void run() {
        count--;
        System.out.println(this.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        for (int i = 0; i < 100; i++) {
            new Thread(thread1, "t" + i).start();
        }
        // 结果可能每次不同，count数字可能重复，因此该类是线程不安全的，可以在run方法上加上 synchronized
    }
}
