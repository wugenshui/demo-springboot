package com.chenbo.daomybatis.controller;

import com.chenbo.daomybatis.entity.User;
import com.chenbo.daomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping("{id}")
    public User getUser(@PathVariable Long id) {
        id = 1094590409767661570L;
        return userService.selectById(id);
    }
}
