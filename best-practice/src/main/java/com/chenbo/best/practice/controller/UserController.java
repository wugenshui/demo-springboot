package com.chenbo.best.practice.controller;

import com.chenbo.best.practice.dto.AjaxResult;
import com.chenbo.best.practice.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Api
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public AjaxResult<UserVo> findById(@PathVariable int id) {
        UserVo user = UserVo.builder().id(id).age(18).name("张三").build();
        return AjaxResult.success(user);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 用户
     */
    @PostMapping
    public AjaxResult<UserVo> saveUser(@Valid @RequestBody UserVo user) {
        return AjaxResult.success(user);
    }
}
