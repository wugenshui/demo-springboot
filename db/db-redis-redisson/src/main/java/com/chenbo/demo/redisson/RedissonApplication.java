package com.chenbo.demo.redisson;

import com.chenbo.demo.redisson.queue.block.BlockQueueTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : chenbo
 * @date : 2020-05-31
 */
@SpringBootApplication
public class RedissonApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedissonApplication.class, args);

        // 分布式锁测试
        // LockTest.test();

        BlockQueueTest.receiver();
        BlockQueueTest.sender();

        // DelayQueueTest.receiver();
        // DelayQueueTest.sender();
    }
}
