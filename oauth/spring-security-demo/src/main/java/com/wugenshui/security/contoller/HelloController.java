package com.wugenshui.security.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
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
