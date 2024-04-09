package com.github.wugenshui.redisson.queue.block;

import com.github.wugenshui.redisson.queue.User;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author : chenbo
 * @date : 2020-06-01
 */
@Component
@SpringBootApplication
public class BlockQueueTest {

    private static final String QUEUE_NAME = "myQueue";
    private static final String REDIS_ADDRESS = "redis://localhost:6379";

    public static void main(String[] args) {
        SpringApplication.run(BlockQueueTest.class, args);
        // 多个消费线程，但只有一个能够消费
        BlockQueueTest.receiver();
        BlockQueueTest.receiver();
        BlockQueueTest.receiver();
        // 生产线程
        BlockQueueTest.sender();
    }

    /**
     * 消息接收者
     */
    public static void receiver() {
        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                // 单机模式
                Config config = new Config();
                config.useSingleServer().setAddress(REDIS_ADDRESS);
                RedissonClient redisson = Redisson.create(config);

                // 获取队列操作对象
                RBlockingQueue<User> queue = redisson.getBlockingQueue(QUEUE_NAME);
                while (true) {
                    // peek 取出首条数据但不删除
                    //User obj = queue.peek();
                    // poll 取出首条数据并删除
                    User obj = queue.poll(10, TimeUnit.MINUTES);
                    if (obj != null) {
                        System.out.println(Thread.currentThread().getName() + ": obj = " + obj);
                    }
                }
            }
        }.start();
    }

    /**
     * 消息发送者
     */
    public static void sender() {
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_ADDRESS);
        RedissonClient redisson = Redisson.create(config);

        // 获取队列操作对象
        RBlockingQueue<User> queue = redisson.getBlockingQueue(QUEUE_NAME);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户姓名:");
            String name = scanner.nextLine();
            User user = User.builder().name(name).age(12).birthday(LocalDateTime.now()).build();
            // 放入元素
            queue.offer(user);
        }
    }
}
