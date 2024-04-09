package com.github.wugenshui.redisson.queue.delay;

import com.github.wugenshui.redisson.queue.User;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 *
 * @author : chenbo
 * @date : 2020-06-01
 */
public class DelayQueueTest {
    /**
     * 消息接收者
     */
    public static void receiver() {
        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Config config = new Config();
                // 单机模式
                config.useSingleServer()
                        .setAddress("redis://192.168.0.222:6379")
                        .setPassword("123456");

                RedissonClient redisson = Redisson.create(config);
                RBlockingQueue<User> blockingFairQueue = redisson.getBlockingQueue("delay_queue");
                RDelayedQueue<User> queue = redisson.getDelayedQueue(blockingFairQueue);
                //queue.offer(User.builder().name("张三").age(12).birthday(LocalDateTime.now()).build());
                while (true) {
                    // 非阻塞，取不出则为null
                    //User obj = queue.poll();
                    // 阻塞
                    User user = blockingFairQueue.take();
                    System.out.println("user = " + user);
                }
            }
        }.start();
    }

    /**
     * 消息发送者
     */
    public static void sender() {
        Config config = new Config();
        // 单机模式
        config.useSingleServer()
                .setAddress("redis://192.168.0.222:6379")
                .setPassword("123456");

        RedissonClient redisson = Redisson.create(config);

        RBlockingQueue<User> blockingFairQueue = redisson.getBlockingQueue("delay_queue");
        RDelayedQueue<User> queue = redisson.getDelayedQueue(blockingFairQueue);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户姓名:");
            String name = scanner.nextLine();
            queue.offer(User.builder().name(name).age(12).birthday(LocalDateTime.now()).build(), 5, TimeUnit.SECONDS);
            System.out.println("已存入用户：" + name);
        }
    }
}
