package com.github.wugenshui.baseutil.baseutil.java.lang.thread;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程三种基础创建方式
 *
 * @author : chenbo
 * @date : 2023-11-02
 */
@SpringBootTest
public class ThreadBaseCreateTest {

    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":Thread1");
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":Thread2");
        }
    }

    static class Thread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + ":Thread3");
            return "Thread3";
        }
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + ":Main");
        new Thread1().start();
        new Thread(new Thread2()).start();
        Thread3 thread3 = new Thread3();
        FutureTask<String> futureTask = new FutureTask<>(thread3);
        new Thread(futureTask).start();
        System.out.println("Thread3返回值" + futureTask.get());

    }
}
