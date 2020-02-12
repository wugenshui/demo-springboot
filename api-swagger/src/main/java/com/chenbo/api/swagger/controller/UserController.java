package com.chenbo.api.swagger.controller;

import com.chenbo.api.swagger.entity.AjaxResult;
import com.chenbo.api.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiOperation("获取指定用户")
    @GetMapping("{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID"),
            @ApiImplicitParam(name = "name", value = "用户名称")
    })
    public AjaxResult<User> getUser(@PathVariable Long id, String name) {
        User user = initUser();
        user.setId(id);
        user.setName(name);
        return AjaxResult.success(user);
    }

    @ApiOperation("保存用户")
    @PostMapping
    @ApiParam(name = "user", value = "用户")
    public AjaxResult<User> saveUser(@RequestBody @Valid User user) {
        return AjaxResult.success(user);
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
