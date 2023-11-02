package com.github.wugenshui.baseutil.baseutil.java.lang.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过ThreadPoolExecutor创建线程池
 *
 * @author : chenbo
 * @date : 2023-11-02
 */
class ThreadCreate3Test {
    @Test
    void goodWayTest() throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        /**
         * corePoolSize 核心线程数，即使线程处于空闲状态，也不会被销毁
         * maximumPoolSize 最大线程数
         *      1. 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务。
         *      2. 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常。因此任务队列需要满足使用场景。
         * keepAliveTime 空闲线程存活时间
         * unit 时间单位
         * workQueue 任务队列
         */
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 1L, TimeUnit.SECONDS, workQueue);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 等待子线程运行完成
        Thread.sleep(1500);
    }

}
