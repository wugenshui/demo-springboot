package com.chenbo.baseutil.java.util.cocurrent;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2020-06-02
 */
public class ThreadPoolExecutorTest {
    /**
     * 自定义线程池
     */
    private static ExecutorService threadPool = new ThreadPoolExecutor(4, 8,
            1L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(64),
            new NamedThreadFactory("AutoRunTask", false));

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------- submit 有返回结果 --------");
        Future<?> future = threadPool.submit(new AutoRunTask());
        System.out.println(Thread.currentThread().getName() + " isDone = " + future.isDone());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " isDone = " + future.isDone());

        System.out.println("-------- execute 没有返回结果 --------");
        threadPool.execute(new AutoRunTask());
    }
}

