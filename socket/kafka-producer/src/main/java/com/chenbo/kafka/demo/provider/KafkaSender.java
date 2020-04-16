package com.chenbo.kafka.demo.provider;

import com.alibaba.fastjson.JSON;
import com.chenbo.kafka.demo.beans.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author : chenbo
 * @date : 2019-11-04
 */
@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息方法
     */
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        String msg = JSON.toJSONString(message);
        log.info("生产数据：{}", msg);
        kafkaTemplate.send("test-kafka", msg);
    }
}