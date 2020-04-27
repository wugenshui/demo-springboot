package com.chenbo.demo.uaa.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2020-04-26
 */
@RestController
@RequestMapping("/test")
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
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
