package com.chenbo.demo.admin.service.controller;

import com.chenbo.demo.admin.service.dto.AjaxResult;
import com.chenbo.demo.admin.service.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Value(value = "${age:12}")
    private int age;

    @ApiOperation("根据id查询用户")
    @ApiParam(name = "id", required = true, value = "用户ID")
    @GetMapping("/{id}")
    public AjaxResult<UserVo> findById(@PathVariable int id) {
        UserVo user = UserVo.builder().id(id).age(age).name("张三").build();
        return AjaxResult.success(user);
    }

    @ApiOperation("保存用户")
    @PostMapping
    public AjaxResult<UserVo> saveUser(@Valid @RequestBody UserVo user) {
        return AjaxResult.success(user);
    }
}
