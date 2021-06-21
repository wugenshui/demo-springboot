package com.chenbo.daomybatisplus.auth.controller;

import com.chenbo.daomybatisplus.auth.entity.JsonInfo;
import com.chenbo.daomybatisplus.auth.mapper.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public Integer save(@RequestBody JsonInfo model) {
        return jsonMapper.insert(model);
    }
}
