package com.chenbo.demo.single.best.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2020-12-23
 */
@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public String test() {
        return "test";
    }
}
