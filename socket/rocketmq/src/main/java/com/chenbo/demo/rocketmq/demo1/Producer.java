package com.chenbo.demo.rocketmq.demo1;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Producer,发送消息
 *
 * @author : chenbo
 * @date : 2021-03-01
 */
public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("demo1");
        producer.setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9877");
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicDemo1", "TagA", ("Hello RocketMQ " + i).getBytes());
            try {
                SendResult sendResult = producer.send(msg);
                System.out.println("sendResult = " + sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }

        producer.shutdown();
    }
}
