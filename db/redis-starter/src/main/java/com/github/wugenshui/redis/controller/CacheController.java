package com.github.wugenshui.redis.controller;

import com.github.wugenshui.redis.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2022-06-18
 */
@RestController
@RequestMapping
public class CacheController {
    @Resource
    private CacheService cacheService;

    /**
     * 查询
     */
    @GetMapping("/{id}")
    public String get(@PathVariable("id") Integer id) {
        return cacheService.get(id);
    }

    /**
     * 保存
     */
    @GetMapping("/{id}/{name}")
    public Map<Integer, String> delete(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return cacheService.save(id, name);
    }
}
