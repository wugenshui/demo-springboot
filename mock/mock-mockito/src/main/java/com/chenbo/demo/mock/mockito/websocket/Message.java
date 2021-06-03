package com.chenbo.demo.mock.mockito.websocket;

import lombok.Data;

@Data
public class Message {
    /**
     * 发送人
     */
    private String to;

    /**
     * 发送的消息
     */
    private String message;
}
