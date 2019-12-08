package com.chenbo.daomongodb.service;

import com.chenbo.daomongodb.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 初始化数据
     */
    @Before
    public void setUp() {
        User user = userService.insert();
        Assert.assertNotNull(user);
        System.out.println("新增用户" + user);
    }

    @Test
    public void insert() {
        User user = userService.insert();
        Assert.assertNotNull(user);
        System.out.println("新增用户" + user);

        user.setPhone("10086");
        user = userService.update(user);
        Assert.assertNotNull(user);
        System.out.println("修改用户" + user);
    }

    @Test
    public void findAll() {
        List<User> users = userService.findAll();
        Assert.assertTrue(users.size() > 0);
        System.out.println("查询所有" + users);

        for (User user : users) {
            Optional<User> queryUser = userService.findById(user.getId());
            Assert.assertNotNull(queryUser);
            System.out.println("查询单个" + queryUser);
        }
    }

    /**
     * 清空数据
     */
    @After
    public void tearDown() {
        userService.deleteAll();
        System.out.println("清空数据");
    }
}