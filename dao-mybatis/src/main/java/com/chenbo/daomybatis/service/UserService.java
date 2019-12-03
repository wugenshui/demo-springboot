package com.chenbo.daomybatis.service;

import com.chenbo.daomybatis.entity.User;
import com.chenbo.daomybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User selectById(Long id) {
        return userMapper.selectById(id);
    }
}
