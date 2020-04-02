package com.chenbo.best.practice.controller;

import com.chenbo.best.practice.dto.AjaxResult;
import com.chenbo.best.practice.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("根据id查询用户")
    @ApiParam(name = "id", required = true, value = "用户ID")
    @GetMapping("/{id}")
    public AjaxResult<UserVo> findById(@PathVariable int id) {
        UserVo user = UserVo.builder().id(id).age(18).name("张三").build();
        return AjaxResult.success(user);
    }

    @ApiOperation("保存用户")
    @PostMapping
    public AjaxResult<UserVo> saveUser(@Valid @RequestBody UserVo user) {
        return AjaxResult.success(user);
    }
}
