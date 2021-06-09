package com.github.wugenshui.db.flyway.controller;

import com.github.wugenshui.db.flyway.entity.Bbb;
import com.github.wugenshui.db.flyway.service.BbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    private BbbService bbbService;

    @GetMapping
    public List<Bbb> test() {
        return bbbService.list();
    }
}
