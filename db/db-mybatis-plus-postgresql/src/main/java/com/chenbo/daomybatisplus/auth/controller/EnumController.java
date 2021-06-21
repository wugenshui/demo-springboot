package com.chenbo.daomybatisplus.auth.controller;

import com.chenbo.daomybatisplus.auth.entity.EnumInfo;
import com.chenbo.daomybatisplus.auth.mapper.EnumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enum")
public class EnumController {
    @Autowired
    private EnumMapper enumMapper;

    @GetMapping
    public List<EnumInfo> list() {
        return enumMapper.selectList(null);
    }


    @PostMapping
    public Integer save(@RequestBody EnumInfo model) {
        return enumMapper.insert(model);
    }
}
