package com.chenbo.demo.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@RestController
public class HelloController {
    @GetMapping("/f1")
    @PreAuthorize("hasAuthority('f1')")
    public String f1() {
        return "f1";
    }

    @GetMapping("/f2")
    @PreAuthorize("hasAuthority('f2')")
    public String f2() {
        return "f2";
    }
}
