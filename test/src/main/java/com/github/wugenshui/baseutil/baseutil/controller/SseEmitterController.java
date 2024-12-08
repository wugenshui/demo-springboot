package com.github.wugenshui.baseutil.baseutil.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EventStream 接口
 *
 * @author : chenbo
 * @date : 2024-06-03
 */
@RestController
@RequestMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@Slf4j
public class SseEmitterController {

    // 测试流式输出： curl -X POST http://localhost:8080/stream
    @PostMapping
    public SseEmitter test(HttpServletResponse response) throws IOException, InterruptedException {
        // 超时时间设置为3s，用于演示客户端自动重连
        SseEmitter sseEmitter = new SseEmitter(30000L);
        // 设置前端的重试时间为1s
        sseEmitter.send(SseEmitter.event().reconnectTime(1000).data("连接成功"));
        sseEmitter.send(SseEmitter.event().name("msg").data("立即发送第一条消息"));
        new Thread(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sseEmitter.send(SseEmitter.event().name("msg").data("延时发送第二条消息"));
                sseEmitter.complete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        sseEmitter.onTimeout(() -> {
            System.out.println("超时");
        });
        sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
        return sseEmitter;
    }

}
