package com.chenbo.dao.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Entity
@Data
public class JpaUser {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Integer deleted;

    private Boolean isAdmin;

    // private List<String> hobby;
}