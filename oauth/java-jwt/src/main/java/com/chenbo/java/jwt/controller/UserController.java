package com.chenbo.java.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenbo.java.jwt.config.UserLoginToken;
import com.chenbo.java.jwt.entity.User;
import com.chenbo.java.jwt.service.UserService;
import com.chenbo.java.jwt.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2020-03-31
 */
@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.findUserById("1");
        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = TokenUtil.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}