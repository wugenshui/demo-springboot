package com.wugenshui.security.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public String root() {
        return "HelloWorld!";
    }
}
