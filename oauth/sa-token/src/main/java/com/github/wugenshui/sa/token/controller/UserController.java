package com.github.wugenshui.sa.token.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录与权限校验测试
 * 1. 访问：http://localhost:8080/user/isLogin 提示未登录
 * 2. 访问：http://localhost:8080/user/doLogin?username=zhang&password=123456  进行登录
 * 3. 访问：http://localhost:8080/user/isLogin 提示已登录
 *
 * @author : chenbo
 * @date : 2023-07-14
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    /**
     * 测试登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    /**
     * 查询登录状态
     *
     * @return 是否登录
     */
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
