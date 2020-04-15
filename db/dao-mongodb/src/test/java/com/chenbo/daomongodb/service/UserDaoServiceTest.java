package com.chenbo.daomongodb.service;

import com.chenbo.daomongodb.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoServiceTest {
    @Autowired
    private UserDaoService userDaoService;

    @Test
    public void insert() {
        User user = userDaoService.insert();
        Assert.assertNotNull(user);
        System.out.println("新增用户" + user);

        user.setPhone("10086");
        user = userDaoService.update(user);
        Assert.assertNotNull(user);
        System.out.println("修改用户" + user);
    }

    @Test
    public void findAll() {
        List<User> users = userDaoService.findAll();
        Assert.assertTrue(users.size() > 0);
        System.out.println("查询所有" + users);

        for (User user : users) {
            Optional<User> queryUser = userDaoService.findById(user.getId());
            Assert.assertNotNull(queryUser);
            System.out.println("查询单个" + queryUser);
        }
    }

    @Test
    public void deleteTest() {
        List<User> users = userDaoService.findAll();
        Assert.assertTrue(users.size() > 0);
        System.out.println("查询所有" + users);

        for (User user : users) {
            userDaoService.delete(user);
        }
    }
}