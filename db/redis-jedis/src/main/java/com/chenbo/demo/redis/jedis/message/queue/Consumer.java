package com.github.wugenshui.redis.jedis.message.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class Consumer extends Thread {

    private String consumerName;
    private volatile int count;
    private Jedis jedis;

    public Consumer(String name) {
        this.consumerName = name;
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

    // 需要不停调用rpop请求，会照成一定程度的资源浪费
    // 也可以设置睡眠时间，但是如果睡眠时间过长，这样不能处理一些时效性的消息，睡眠时间过短，也会在连接上造成比较大的开销。
    //public void processMessage() {
    //    String message = jedis.rpop(Producer.MESSAGE_KEY);
    //    if (message != null) {
    //        handle(message);
    //    }
    //}

    public void processMessage() {    /**
     * brpop支持多个列表(队列)
     * brpop指令是支持队列优先级的，比如这个例子中MESSAGE_KEY的优先级大于testKey（顺序决定）。
     * 如果两个列表中都有元素，会优先返回优先级高的列表中的元素，所以这儿优先返回MESSAGE_KEY
     * 0表示不限制等待，会一直阻塞在这儿
     */
        List<String> messages = jedis.brpop(0, Producer.MESSAGE_KEY, "testKey");
        if (messages.size() != 0) {
            //由于该指令可以监听多个Key,所以返回的是一个列表
            //列表由2项组成，1) 列表名，2)数据
            String keyName = messages.get(0);
            //如果返回的是MESSAGE_KEY的消息
            if (Producer.MESSAGE_KEY.equals(keyName)) {
                String message = messages.get(1);
                handle(message);
            }

        }
        System.out.println("=======================");
    }

    public void handle(String message) {
        count++;
        System.out.println(consumerName + " 正在处理消息,消息内容是: " + message + " 这是第" + count + "条");
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer("queue-customer");
        consumer.start();
    }
}