package com.github.wugenshui.redis.subpub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author : chenbo
 * @date : 2022-06-17
 */
@Component
public class MessageSend {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 定时发送消息
     */
    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        // 发送端用字符串传递，接收端可以根据channel进行不同类型转换
        redisTemplate.convertAndSend("msg", String.valueOf(new Random().nextInt(100)));
        redisTemplate.convertAndSend("msg2", "msg2");
        redisTemplate.convertAndSend("single", "single");
    }
}
