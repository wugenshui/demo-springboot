package com.github.wugenshui.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2022-01-28
 */
@Data
@AllArgsConstructor
public class User {
    private Long uid;

    private String username;

    private String password;
}
