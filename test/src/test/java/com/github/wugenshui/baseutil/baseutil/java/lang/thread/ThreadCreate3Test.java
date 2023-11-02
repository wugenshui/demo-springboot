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
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(20);
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 1L, TimeUnit.SECONDS, queue);
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
