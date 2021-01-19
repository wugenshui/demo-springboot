package com.chenbo.demo.redission.starter.consumer;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2021-01-19
 */
@Component
public class RedissionConsumer implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run(String... args) {
        // 新建线程执行逻辑
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.submit(() -> {
            while (true) {
                try {
                    RBlockingQueue<String> queue = redissonClient.getBlockingQueue("anyQueue");
                    System.out.println("queue.take() = " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
