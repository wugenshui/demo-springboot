package com.chenbo.daomybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Integer deleted;
}