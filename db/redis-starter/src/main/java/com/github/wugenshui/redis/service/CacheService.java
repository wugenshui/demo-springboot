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

    private Map<Integer, String> userMap = new HashMap() {{
        userMap.put(1, "张三");
        userMap.put(2, "李四");
    }};

    @Cacheable(value = "user", key = "#id")
    public String get(Integer id) {
        System.out.println("获取内存中的用户名，并加入缓存");
        return userMap.get(id);
    }

    /**
     * 删除单个
     * 删除全部的写法为：  @CacheEvict(value = "user", allEntries = true)
     *
     * @param id   用户id
     * @param name 用户名
     * @return 用户名集合
     */
    @CacheEvict(value = "user", key = "#id")
    public Map<Integer, String> save(Integer id, String name) {
        System.out.println("清空缓存，保存内存中的用户名");
        userMap.put(id, name);
        return userMap;
    }
}
