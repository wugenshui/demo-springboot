package com.chenbo.baseutil.thread;

/**
 * volatile 不具备 synchronized 原子性（同步）,可使用 AtomicInteger
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread5 extends Thread {

    private static volatile int count = 0;
    //private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
            //count.incrementAndGet();
        }
        System.out.println("count = " + count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread5().start();
        }
    }
}
