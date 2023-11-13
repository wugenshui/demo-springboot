package com.github.wugenshui.aop.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2023-11-13
 */
@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public String test() {
        return "test";
    }
}
