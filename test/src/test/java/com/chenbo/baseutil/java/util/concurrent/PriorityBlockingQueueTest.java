package com.chenbo.baseutil.java.util.concurrent;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 基于优先级的阻塞队列
 *
 * @author : chenbo
 * @date : 2021-02-14
 */
@SpringBootTest
public class PriorityBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        queue.put(new Task(3, "任务3"));
        queue.put(new Task(7, "任务7"));
        queue.put(new Task(1, "任务1"));

        System.out.println("queue = " + queue);
        System.out.println("取出元素 = " + queue.take());
        System.out.println("queue = " + queue);
    }

}
