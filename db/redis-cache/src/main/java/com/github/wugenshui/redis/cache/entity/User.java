package com.github.wugenshui.redis.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deleted;
}
