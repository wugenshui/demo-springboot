package com.chenbo.baseutil.java.util.cocurrent;

/**
 * 简单的运行任务
 *
 * @author : chenbo
 * @date : 2020-06-02
 */
public class AutoRunTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " i = " + i);
        }
    }
}