package com.chenbo.demo.redis.jedis;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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

    @Test
    public void transTest() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "张三");
        jsonObject.put("hello", "world");

        JedisPool jedisPool = new JedisPool("192.168.192.170", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("1q2w#E$R");

        // 清空数据
        jedis.flushDB();

        // 开启事务
        Transaction multi = jedis.multi();

        try {
            String result = jsonObject.toString();
            multi.set("user1", result);
            // 主动触发异常，事务不会执行
            int i = 1 / 0;
            multi.set("user2", result);
            // 执行事务
            multi.exec();
        } catch (Exception e) {
            // 放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 关闭连接
            jedis.close();
        }
    }
}