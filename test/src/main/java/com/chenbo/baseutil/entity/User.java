package com.chenbo.baseutil.entity;

import lombok.Data;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@Data
public class User {

    private int id;

    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
    }
}