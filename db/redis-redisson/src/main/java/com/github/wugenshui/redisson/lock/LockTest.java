package com.github.wugenshui.redisson.lock;

import com.github.wugenshui.redisson.queue.block.BlockQueueTest;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分布式锁测试
 * 利用 redis hash 实现
 *
 * @author : chenbo
 * @date : 2020-05-31
 */
@SpringBootApplication
public class LockTest {

    public static void main(String[] args) {
        SpringApplication.run(BlockQueueTest.class, args);

        Config config = new Config();

        // 集群模式
        //config.useClusterServers()
        //        .addNodeAddress("redis://192.168.192.170:6379")
        //        .setPassword("123456");

        // 单机模式
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        //.setAddress("redis://192.168.0.222:6379")
        //.setPassword("123456");

        final RedissonClient redisson = Redisson.create(config);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RLock lock = redisson.getLock("myLock");
                    System.out.println(Thread.currentThread().getName() + " 获取锁对象");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取锁");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放");
                }
            }).start();
        }
    }
}
