package com.chenbo.daomybatis.service.impl;

import com.chenbo.daomybatis.entity.User;
import com.chenbo.daomybatis.mapper.UserMapper;
import com.chenbo.daomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2019/12/3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
