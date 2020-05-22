package com.chenbo.demo.redis.jedis.message.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class Customer extends Thread {

    private String customerName;
    private volatile int count;
    private Jedis jedis;

    public Customer(String name) {
        this.customerName = name;
        init();
    }

    private void init() {
        JedisPool jedisPool = new JedisPool("192.168.0.222", 6379);
        jedis = jedisPool.getResource();
        jedis.auth("123456");
    }

    @Override
    public void run() {
        while (true) {
            processMessage();
        }
    }

    public void processMessage() {
        String message = jedis.rpop(Producer.MESSAGE_KEY);
        if (message != null) {
            count++;
            handle(message);
        }
    }

    public void handle(String message) {
        System.out.println(customerName + " 正在处理消息,消息内容是: " + message + " 这是第" + count + "条");
    }

    public static void main(String[] args) {
        Customer customer = new Customer("queue-customer");
        customer.start();
    }
}