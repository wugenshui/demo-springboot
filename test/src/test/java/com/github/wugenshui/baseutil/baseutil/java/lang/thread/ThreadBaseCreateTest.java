package com.github.wugenshui.baseutil.baseutil.java.lang.thread;


import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程三种基础创建方式
 *
 * @author : chenbo
 * @date : 2023-11-02
 */
class ThreadBaseCreateTest {

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Thread3 implements Callable<String> {
        @Override
        public String call() {
            System.out.println(Thread.currentThread().getName());
            return "Thread3";
        }
    }

    @Test
    void createTest() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        new Thread1().start();
        new Thread(new Thread2()).start();
        Thread3 thread3 = new Thread3();
        FutureTask<String> futureTask = new FutureTask<>(thread3);
        new Thread(futureTask).start();
        System.out.println(Thread.currentThread().getName() + ":" + futureTask.get());
    }


    @Test
    void finishThread() {
        // 结束线程测试
        new Thread(() -> {
            Thread thread = Thread.currentThread();
            thread.interrupt();
            for (int i = 0; i < 50000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程已经结束，退出线程");
                    return;
                }
            }
            System.out.println("我是for下面的语句，我被执行说明线程没有真正结束");
        }).start();
        //Thread.sleep(1000);
    }
}
