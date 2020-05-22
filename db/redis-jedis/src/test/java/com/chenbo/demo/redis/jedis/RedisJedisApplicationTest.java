package com.chenbo.demo.redis.jedis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : chenbo
 * @date : 2020-05-21
 */
@SpringBootTest
public class RedisJedisApplicationTest {
    @Test
    public void JedisConfigTest() {
        // 普通连接
        Jedis jedis = new Jedis("192.168.0.222", 6379);
        jedis.auth("123456");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        Assert.assertEquals("bar", value);
        System.out.println("value = " + value);

        // 连接池
        JedisPool jedisPool = new JedisPool("192.168.0.222", 6379);
        jedis = jedisPool.getResource();
        jedis.auth("123456");
        jedis.set("foo", "bar");
        value = jedis.get("foo");
        Assert.assertEquals("bar", value);
        System.out.println("value = " + value);
    }
}