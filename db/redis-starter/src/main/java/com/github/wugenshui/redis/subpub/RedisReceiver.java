package com.github.wugenshui.redis.subpub;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author : chenbo
 * @date : 2022-06-17
 */
@Component
public class RedisReceiver {
    /**
     * 发布订阅模式，当订阅收到消息就会立即处理
     * 同时可以处理多条，所以多条处理完毕是同一时间打印出来的
     */
    public void handleMessage(Serializable message, String channel) throws InterruptedException {
        System.out.println(channel + "开始处理:" + message);
        Thread.sleep(5000);
        System.out.println(channel + "处理完毕:" + message);
    }
}
