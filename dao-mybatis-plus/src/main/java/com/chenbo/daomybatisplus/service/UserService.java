package com.chenbo.daomybatisplus.service;

import com.chenbo.daomybatisplus.entity.User;
import com.chenbo.daomybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public List<User> selectList() {
        return userMapper.selectList(null);
    }

    public User selectById(int id) {
        return userMapper.selectById(id);
    }
}
