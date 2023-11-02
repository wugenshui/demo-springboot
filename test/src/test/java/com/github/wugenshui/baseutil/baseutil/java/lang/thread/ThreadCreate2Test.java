package com.github.wugenshui.baseutil.baseutil.java.lang.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过Executors创建线程池
 *
 * @author : chenbo
 * @date : 2023-11-02
 */
class ThreadCreate2Test {
    @Test
    void newSingleThreadExecutorTest() throws InterruptedException {
        // 创建一个单线程化线程池，它只能用一个工作线程来执行任务
        // 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println("newSingleThreadExecutor\t" + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 等待子线程运行完成
        Thread.sleep(500);
    }

    @Test
    void newCachedThreadPoolTest() throws InterruptedException {
        // 缓存线程池，先查看线程池中有没有之前创建的线程，如果有则直接使用。否则就新创建一个新的线程加入线程池中
        // 常用于执行一些业务处理时间很短的任务。
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            try {
                // 模拟线程调用间隔时间，执行完的线程会释放并复用
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                System.out.println("newCachedThreadPool\t" + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 等待子线程运行完成
        Thread.sleep(500);
    }

    @Test
    void newFixedThreadPoolTest() throws InterruptedException {
        // 创建一个固定（指定）长度可重用的线程池，可以控制最大创建数，超过最大长度之后就会放入到队列进行等待。
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                System.out.println("newFixedThreadPool\t" + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 等待子线程运行完成
        Thread.sleep(500);
    }
}
