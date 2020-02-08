package com.chenbo.api.swagger.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@Data
public class User {
    private Long id;

    private String name;

    private Integer age;

    private LocalDateTime createTime;
}
