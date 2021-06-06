package com.chenbo.demo.best.admin.service.controller;

import com.chenbo.demo.best.admin.service.dto.AjaxResult;
import com.chenbo.demo.best.admin.service.entity.User;
import com.chenbo.demo.best.admin.service.service.IUserService;
import com.chenbo.demo.best.admin.service.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value(value = "${age:12}")
    private int age;

    @Autowired
    private IUserService userService;

    @ApiOperation("保存用户")
    @PostMapping
    public AjaxResult<Boolean> insert(@Validated @RequestBody UserVo vo) {
        User user = new User();
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        BeanUtils.copyProperties(vo, user);
        return AjaxResult.success(userService.save(user));
    }

    @ApiOperation("根据id查询用户")
    @ApiParam(name = "id", required = true, value = "用户ID")
    @GetMapping("/{id}")
    public AjaxResult<UserVo> findById(@PathVariable Long id) {
        UserVo userVo = new UserVo();
        User user = userService.getById(id);
        BeanUtils.copyProperties(user, userVo);
        return AjaxResult.success(user);
    }
}
