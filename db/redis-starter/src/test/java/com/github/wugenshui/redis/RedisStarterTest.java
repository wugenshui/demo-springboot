package com.github.wugenshui.redis;

import com.github.wugenshui.redis.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author : chenbo
 * @date : 2020-05-24
 */
@SpringBootTest
@Slf4j
class RedisStarterTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisSerialTemplate;

    @Test
    void userTest() {
        String key = "user:0";
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 单个实体 set\get
        User user = new User(0L, "aaa", "666666");
        operations.set(key, user);
        User getUser = operations.get(key);
        Assertions.assertEquals(getUser.getPassword(), user.getPassword());

        // 一次性设置获取多个值
        Map<String, User> maps = new HashMap<>();
        maps.put("user:1", new User(1L, "chenzz", "111111"));
        maps.put("user:2", new User(2L, "chenym", "222222"));
        maps.put("user:3", new User(3L, "zhoubq", "333333"));
        maps.put("user:4", new User(4L, "zhaoyi", "444444"));
        maps.put("user:5", new User(5L, "panwnz", "555555"));

        operations.multiSet(maps);
        User user3 = operations.get("user:3");
        log.info("[第3个] - [{}]", user3.toString());

        // 一次获取多个值
        List<String> keys = new ArrayList<>();
        keys.add("user:2");
        keys.add("user:5");
        List<User> multiValues = operations.multiGet(keys);
        multiValues.forEach(x -> log.info("[{}]: {}", x.getUsername(), x.getPassword()));

        // 设置值时设置过期时间(2分钟后过期)
        operations.set("user:timeout", new User(20L, "czz", "xxxxxx"), Duration.ofMinutes(2));

        Set<String> allKeys = redisTemplate.keys("*user*");
        allKeys.forEach(x -> log.info("key=" + x));
    }


    @Test
    public void testSerialzable() {
        String key = "system:user:1000";
        if (!redisSerialTemplate.hasKey(key)) {
            log.info("[不存在key] - [{}]", key);
        }
        redisSerialTemplate.opsForValue().set(key, new User(1000L, "admin", "666666"));
        final User user = (User) redisSerialTemplate.opsForValue().get(key);
        log.info("[对象结果] - [{}]", user);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void strRedisTest() {
        // 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("system:config:up", 1))
        );
        log.info("[up] - [{}]", stringRedisTemplate.opsForValue().get("system:config:up"));

        stringRedisTemplate.opsForValue().set("system:config:xx", "200");
        final String k1 = stringRedisTemplate.opsForValue().get("system:config:xx");
        log.info("[字符缓] - [{}]", k1);
    }

    @Test
    public void strRedisKesTest() {
        Map<String, String> maps = new HashMap<>();
        maps.put("system:user:1", "chenzz");
        maps.put("system:user:2", "chenym");
        maps.put("system:user:3", "zhoubq");
        maps.put("system:user:4", "zhaoyi");
        maps.put("system:user:5", "panwnz");

        stringRedisTemplate.opsForValue().multiSet(maps);

        Set<String> allkeys = stringRedisTemplate.keys("system:user:*");
        allkeys.forEach(x -> log.info("key=" + x));
    }
}
