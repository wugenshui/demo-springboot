package com.github.wugenshui.redisson.queue.delay;

import com.github.wugenshui.redisson.queue.User;
import com.github.wugenshui.redisson.queue.block.BlockQueueTest;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 *
 * @author : chenbo
 * @date : 2020-06-01
 */
@SpringBootApplication
public class DelayQueueTest {

    private static final String QUEUE_NAME = "myDelayQueue";
    private static final String REDIS_ADDRESS = "redis://localhost:6379";

    /**
     * 消息接收者
     */
    public static void main(String[] args) {
        SpringApplication.run(DelayQueueTest.class, args);
        receiver();
        receiver();
        receiver();
        sender();
    }

    public static void receiver() {
        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Config config = new Config();
                // 单机模式
                config.useSingleServer().setAddress(REDIS_ADDRESS);

                RedissonClient redisson = Redisson.create(config);
                RBlockingQueue<User> blockingFairQueue = redisson.getBlockingQueue(QUEUE_NAME);
                RDelayedQueue<User> queue = redisson.getDelayedQueue(blockingFairQueue);
                //queue.offer(User.builder().name("张三").age(12).birthday(LocalDateTime.now()).build());
                while (true) {
                    // 非阻塞，取不出则为null
                    //User obj = queue.poll();
                    // 阻塞
                    User user = blockingFairQueue.take();
                    System.out.println(Thread.currentThread().getName() + " user = " + user);
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
        config.useSingleServer().setAddress(REDIS_ADDRESS);

        RedissonClient redisson = Redisson.create(config);

        RBlockingQueue<User> blockingFairQueue = redisson.getBlockingQueue(QUEUE_NAME);
        RDelayedQueue<User> queue = redisson.getDelayedQueue(blockingFairQueue);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 100; i++) {
            int delaySeconds = 3;
            User user = User.builder().name("用户" + i).age(i).birthday(LocalDateTime.now()).build();
            queue.offer(user, delaySeconds, TimeUnit.SECONDS);
            System.out.println("已存入用户：" + user.getName() + ", 延迟" + delaySeconds + "秒进行消费");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
