package com.chenbo.demo.admin.service.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2020-04-26
 */
@Api(tags = "测试认证授权接口")
@RestController
@RequestMapping("/test")
@Slf4j
public class TestOaurhController {

    @GetMapping("/f1")
    @PreAuthorize("hasAnyAuthority('f1')")
    public String f1() {
        return getUser() + "访问资源1";
    }

    @GetMapping("/f2")
    @PreAuthorize("hasAnyAuthority('f2')")
    public String f2() {
        return getUser() + "访问资源2";
    }


    @GetMapping("/f3")
    public String f3() {
        return getUser() + "访问资源3";
    }

    private String getUser() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("访问接口用户：" + principal);
        if (principal == null) {
            username = "匿名访问者";
        } else if (principal instanceof User) {
            User user = (User) principal;
            username = user.getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
