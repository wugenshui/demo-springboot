package com.chenbo.daomongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Data
@Document("user")
public class User implements Serializable {

    @Id
    private String id;

    private String username;

    private String loginname;

    private String password;

    private Date registerTime;

    private String phone;

    private String sex;

    private int age;

    private String[] favourite;

    private UserPost post;
}