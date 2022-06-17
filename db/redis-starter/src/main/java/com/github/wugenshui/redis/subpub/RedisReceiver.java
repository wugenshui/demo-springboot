package com.github.wugenshui.redis.subpub;

import org.springframework.stereotype.Component;

/**
 * @author : chenbo
 * @date : 2022-06-17
 */
@Component
public class RedisReceiver {
    public void handleMessage(String message) {
        System.out.println("subscribe receive message:" + message);
    }
}
