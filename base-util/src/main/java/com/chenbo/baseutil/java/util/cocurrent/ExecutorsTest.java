package com.chenbo.baseutil.java.util.cocurrent;

import java.util.concurrent.Executors;

/**
 * @author : chenbo
 * @date : 2020-06-02
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        Executors.newSingleThreadExecutor().submit(() -> {
            Thread thread = Thread.currentThread();
            //System.out.println("Thread.currentThread().getName() = " + thread.getName());
            //Thread.interrupted();
            thread.interrupt();

            for (int i = 0; i < 50000; i++) {
                if (thread.isInterrupted()) {
                    System.out.println("线程已经结束，我要退出");
                    return;
                    //break;
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("我是for下面的语句，我被执行说明线程没有真正结束");


        });
    }
}
