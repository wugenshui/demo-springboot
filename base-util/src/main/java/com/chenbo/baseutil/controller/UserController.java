package com.chenbo.baseutil.controller;

import com.chenbo.baseutil.entity.AjaxResult;
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
public class UserController {

    @GetMapping("")
    public AjaxResult getUser() {
        return AjaxResult.success(123);
    }
}
