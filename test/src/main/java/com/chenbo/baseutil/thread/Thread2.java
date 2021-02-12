package com.chenbo.baseutil.thread;

/**
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread2 {
    private int num = 0;

    public synchronized void printNum(String tag) {
        if (tag.equals("a")) {
            num = 100;
            System.out.println("tag a set num = 100");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("tag b set num = 200");
        }
        System.out.println("tag = " + tag + ", num = " + num);
    }

    public static void main(String[] args) {
        Thread2 threadA = new Thread2();
        Thread2 threadB = new Thread2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadA.printNum("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadB.printNum("b");
            }
        });
        t1.start();
        t2.start();
        // 两个线程未锁定同一对象
    }
}
