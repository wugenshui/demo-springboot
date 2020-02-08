package com.chenbo.api.swagger.controller;

import com.chenbo.api.swagger.entity.AjaxResult;
import com.chenbo.api.swagger.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("{id}")
    public AjaxResult<User> getUser(@PathVariable Long id) {
        User user = initUser();
        user.setId(id);
        return AjaxResult.success(user);
    }

    @PostMapping
    public AjaxResult<User> saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            List<String> mesList = new ArrayList<String>();
            for (int i = 0; i < errorList.size(); i++) {
                mesList.add(errorList.get(i).getDefaultMessage());
            }
            return AjaxResult.error("保存用户异常：" + mesList.toString());
        } else {
            return AjaxResult.success(user);
        }
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
