package com.github.wugenshui.redis.subpub;

import org.springframework.stereotype.Component;

/**
 * @author : chenbo
 * @date : 2022-06-17
 */
@Component
public class RedisReceiver {
    /**
     * 发布订阅模式，当订阅收到消息就会立即处理
     */
    public void handleMessage(String message) throws InterruptedException {
        System.out.println("开始处理:" + message);
        Thread.sleep(5000);
        System.out.println("处理完毕:" + message);
    }
}
