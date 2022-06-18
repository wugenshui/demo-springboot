package com.github.wugenshui.redis.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2022-06-18
 */
@Service
public class CacheService {
    private Map<Integer, String> map = new HashMap<>();

    {
        map.put(1, "张三");
        map.put(2, "李四");
    }

    private String name = "张三";

    @Cacheable(value = "user", key = "#id")
    public String get(Integer id) {
        System.out.println("获取内存中的用户名，并加入缓存");
        return map.get(id);
    }

    // 删除全部
    // @CacheEvict(value = "user", allEntries = true)
    // 删除单个
    @CacheEvict(value = "user", key = "#id")
    public Map<Integer, String> save(Integer id, String name) {
        System.out.println("清空缓存，保存内存中的用户名");
        map.put(id, name);
        return map;
    }
}
