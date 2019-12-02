package com.chenbo.daomybatis.controller;

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

    @RequestMapping("{id}")
    public String getUser(@PathVariable double id) {
        return userService.selectById(id).toString();
    }
}
