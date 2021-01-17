package com.chenbo.demo.spring.cloud.alibaba.rocketmq.producer;

import com.chenbo.demo.spring.cloud.alibaba.rocketmq.config.SystemConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向发送消息
 *
 * @author : chenbo
 * @date : 2021-01-17
 */
public class OnewayProducer {
    public static void sendMsg() throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer(SystemConfig.GROUP);
        // 设置NameServer的地址
        producer.setNamesrvAddr(SystemConfig.ADDRESS);
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(SystemConfig.TOPIC,
                    SystemConfig.TAG,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
