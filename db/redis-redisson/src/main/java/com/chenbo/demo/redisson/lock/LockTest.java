package com.chenbo.demo.redisson.lock;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : chenbo
 * @date : 2020-05-31
 */
@SpringBootApplication
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
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

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("进入run方法" + Thread.currentThread().getName());
                    RLock lock = redisson.getLock("myLock");
                    System.out.println("获取锁对象" + Thread.currentThread().getName());
                    lock.lock();
                    System.out.println("获取锁" + Thread.currentThread().getName());
                    Thread.sleep(5000);
                    lock.unlock();
                    System.out.println("释放锁" + Thread.currentThread().getName());
                }
            }).start();
        }
    }
}
