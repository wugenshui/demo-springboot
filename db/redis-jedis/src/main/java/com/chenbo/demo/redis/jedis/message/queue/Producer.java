package com.chenbo.demo.redis.jedis.message.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class Producer extends Thread {

    public static final String MESSAGE_KEY = "message:queue";
    private Jedis jedis;
    private String producerName;
    private volatile int count;

    public Producer(String name) {
        this.producerName = name;
        init();
    }

    private void init() {
        JedisPool jedisPool = new JedisPool("192.168.0.222", 6379);
        jedis = jedisPool.getResource();
        jedis.auth("123456");
    }

    public void putMessage(String message) {
        Long size = jedis.lpush(MESSAGE_KEY, message);
        System.out.println(producerName + ": 当前未被处理消息条数为:" + size);
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 输入当前时间
                putMessage(String.valueOf(System.currentTimeMillis()));
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer("queue-producer");
        producer.start();

        for (; ; ) {
            System.out.println("main : 累计发送消息条数:" + producer.getCount());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
