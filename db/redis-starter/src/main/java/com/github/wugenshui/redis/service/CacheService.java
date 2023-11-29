package com.github.wugenshui.redis.service;

import com.github.wugenshui.redis.util.RedisUtil;
import com.github.wugenshui.redis.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2022-06-18
 */
@Service
public class CacheService {

    @Resource
    private RedisUtil redisUtil;

    private Map<Integer, String> userMap = new HashMap();

    {
        userMap.put(1, "张三");
        userMap.put(2, "李四");
    }

    public User get(Integer id) {
        String key = "spring:data:user:" + id;
        Object obj = redisUtil.get(key);
        if (obj == null) {
            redisUtil.set(key, userMap.get(id));
        }
        return (User) redisUtil.get(key);
    }

    public Map<Integer, String> save(Integer id, String name) {
        System.out.println("保存用户");
        userMap.put(id, name);
        return userMap;
    }
}
