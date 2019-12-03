package com.chenbo.daomybatis.controller;

import com.chenbo.daomybatis.entity.User;
import com.chenbo.daomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        id = 1094590409767661570L;
        return userService.selectById(id);
    }


    /**
     * 获取用户信息
     *
     * @param user 用户
     * @return
     */
    @PostMapping("")
    public int saveUser(@RequestBody User user) {
        return userService.insert(user);
    }
}
