package com.github.wugenshui.single.best.practice.practice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.wugenshui.single.best.practice.practice.entity.User;
import com.github.wugenshui.single.best.practice.practice.mapper.UserMapper;
import com.github.wugenshui.single.best.practice.practice.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
@Api("用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @GetMapping
    public List<User> getUser() {
        return userService.list();
    }

    @GetMapping("/{name}")
    public List<User> getUser(@PathVariable String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, name);
        return userService.list(queryWrapper);
    }

    @GetMapping("/lambda/{name}")
    public List<User> getLambdaUser(@PathVariable String name) {
        return new LambdaQueryChainWrapper<>(userMapper)
                .eq(User::getName, name)
                .list();
    }
}
