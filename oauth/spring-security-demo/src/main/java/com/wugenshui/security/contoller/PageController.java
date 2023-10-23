package com.wugenshui.security.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@Controller
public class PageController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        return "hello";
    }
}
