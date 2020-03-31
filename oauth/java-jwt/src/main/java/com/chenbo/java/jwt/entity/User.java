package com.chenbo.java.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chenbo
 * @date : 2020-03-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String username;
    String password;
}