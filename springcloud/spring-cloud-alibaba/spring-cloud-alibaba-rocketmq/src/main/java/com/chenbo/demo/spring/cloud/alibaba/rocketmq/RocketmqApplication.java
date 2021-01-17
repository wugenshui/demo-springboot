package com.chenbo.demo.spring.cloud.alibaba.rocketmq;

import com.chenbo.demo.spring.cloud.alibaba.rocketmq.config.SystemConfig;
import com.chenbo.demo.spring.cloud.alibaba.rocketmq.producer.AsyncProducer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : chenbo
 * @date : 2021-01-15
 */
@SpringBootApplication
public class RocketmqApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RocketmqApplication.class, args);

        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(SystemConfig.GROUP);
        consumer.setVipChannelEnabled(false);
        // 设置NameServer的地址
        consumer.setNamesrvAddr(SystemConfig.ADDRESS);

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe(SystemConfig.TOPIC, "*");
        // 注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msg, context) -> {
            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msg);
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");

        // SyncProducer.sendMsg();
        AsyncProducer.sendMsg();
    }

}
