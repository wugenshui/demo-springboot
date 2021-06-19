package com.chenbo.daomybatisplus.auth.controller;

import com.chenbo.daomybatisplus.auth.entity.JsonInfo;
import com.chenbo.daomybatisplus.auth.mapper.JsonMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {
    @Autowired
    private JsonMapper jsonMapper;

    @GetMapping
    public List<JsonInfo> list() {
        return jsonMapper.selectList(null);
    }
}
