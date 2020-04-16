package com.chenbo.kafka.demo.beans;

import lombok.Data;

import java.util.Date;

/**
 * @author : chenbo
 * @date : 2019-11-04
 */
@Data
public class Message {
    /**
     * id
     */
    private Long id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 时间戳
     */
    private Date sendTime;

}