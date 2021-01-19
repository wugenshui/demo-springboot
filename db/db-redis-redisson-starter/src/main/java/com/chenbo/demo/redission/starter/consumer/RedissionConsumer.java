package com.chenbo.demo.redission.starter.consumer;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        while (true) {
            try {
                RBlockingQueue<String> queue = redissonClient.getBlockingQueue("anyQueue");
                System.out.println("queue.take() = " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
