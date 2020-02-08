package com.chenbo.api.swagger.controller;

import com.chenbo.api.swagger.entity.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        User user = initUser();
        user.setId(id);
        return user;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return user;
    }

    private User initUser() {
        User user = new User();
        user.setId(10086L);
        user.setName("张三");
        user.setAge(18);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }
}
