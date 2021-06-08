package com.github.wugenshui.baseutil.baseutil.controller;

import com.github.wugenshui.baseutil.baseutil.entity.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("")
    public AjaxResult<String> getUser() {
        return AjaxResult.success("返回用户信息");
    }
}
