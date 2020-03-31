package com.chenbo.java.jwt.service;

import com.chenbo.java.jwt.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2020-03-31
 */
@Service
public class UserService {
    public User findUserById(String userId) {
        return new User("1", "张三", "123");
    }
}
