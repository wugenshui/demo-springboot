package com.github.wugenshui.sa.token.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录与权限校验测试
 * 1. 访问：http://localhost:8080/user/isLogin 提示未登录
 * 2. 访问：http://localhost:8080/user/doLogin?username=zhang&password=123456  进行登录
 * 3. 访问：http://localhost:8080/user/isLogin 提示已登录
 * 4. 访问：http://localhost:8080/user/tokenInfo 查询令牌信息
 * 5. 访问：http://localhost:8080/user/logout 登出
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
    public SaResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    /**
     * 查询登录状态
     *
     * @return 是否登录
     */
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    /**
     * 查询 Token 信息
     * @return
     */
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    /**
     * 测试注销
     * @return
     */
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }
}
