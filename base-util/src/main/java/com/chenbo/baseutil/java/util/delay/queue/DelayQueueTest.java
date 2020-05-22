package com.chenbo.baseutil.java.util.delay.queue;

import cn.hutool.core.date.DateTime;
import com.chenbo.baseutil.util.RandomUtil;
import lombok.SneakyThrows;

import java.util.concurrent.DelayQueue;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class DelayQueueTest extends Thread {
    public static DelayQueue<Message> queue = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    int millis = RandomUtil.getRandomNumber(500, 1500);
                    queue.put(new Message("休眠时间" + millis, DateTime.now()));
                    System.out.println("插入文本，休眠时间：" + millis);
                    Thread.sleep(millis);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    Message msg = queue.take();
                    System.out.println("msg = " + msg);
                }
            }
        }).start();

    }

}
