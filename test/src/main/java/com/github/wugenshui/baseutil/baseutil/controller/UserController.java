package com.github.wugenshui.baseutil.baseutil.controller;

import com.github.wugenshui.baseutil.baseutil.entity.AjaxResult;
import com.github.wugenshui.baseutil.baseutil.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author : chenbo
 * @date : 2019-12-07
 */
@RestController
@RequestMapping("/user")
@Api
@ApiOperation("用户")
public class UserController {

    @Bean
    public User user() {
        User user = new User();
        user.setUsername("张三");
        return user;
    }

    @Bean
    public User lisi() {
        User user = new User();
        user.setUsername("lisi");
        return user;
    }

    @Autowired
    private User user;

    @Autowired
    private User lisi;

    @GetMapping
    public AjaxResult<String> getUser() {
        return AjaxResult.success("返回用户信息" + user.getUsername() + lisi.getUsername());
    }
}
