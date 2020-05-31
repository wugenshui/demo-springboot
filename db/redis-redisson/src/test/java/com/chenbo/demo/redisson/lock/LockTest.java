package com.chenbo.demo.redisson.lock;

import lombok.SneakyThrows;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : chenbo
 * @date : 2020-05-31
 */
@SpringBootTest
public class LockTest {
    @Test
    public void Test() {
        Config config = new Config();

        // 集群模式
        //config.useClusterServers()
        //        .addNodeAddress("redis://192.168.192.170:6379")
        //        .setPassword("123456");

        // 单机模式
        config.useSingleServer()
                .setAddress("redis://192.168.192.170:6379")
                .setPassword("123456");

        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("myLock");

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "lock");
                    Thread.sleep(5000);
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "unlock");
                }
            });
        }
    }
}
