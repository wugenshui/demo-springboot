package com.chenbo.daomybatisplus.auth.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
@RequestMapping("/auth/user")
public class UserController {

    @GetMapping
    public LocalDateTime getUser(LocalDateTime dateTime) {
        return dateTime;
    }
}

