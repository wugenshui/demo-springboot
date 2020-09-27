package com.chenbo.dbcp.druid.controller;


import com.chenbo.dbcp.druid.entity.User;
import com.chenbo.dbcp.druid.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public List<User> getUser(Date dateTime) {
        return userService.list();
    }
}

