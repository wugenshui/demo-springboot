package com.chenbo.demo.redisson;

import com.chenbo.demo.redisson.queue.delay.DelayQueueTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : chenbo
 * @date : 2020-05-31
 */
@SpringBootApplication
public class RedissonApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RedissonApplication.class, args);

        // 分布式锁测试
        //LockTest.test();

        // BlockQueueTest.receiver();
        // DelayQueueTest.sender();

        //DelayQueueTest.receiver();
        DelayQueueTest.sender();
    }
}
