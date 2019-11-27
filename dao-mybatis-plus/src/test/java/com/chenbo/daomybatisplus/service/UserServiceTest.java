package com.chenbo.daomybatisplus.service;

import com.chenbo.daomybatisplus.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : chenbo
 * @date : 2019-11-27
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void selectById() {
        User user = userService.selectById(1);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    void insert() {
        User user = new User();
        user.setId(10);
        user.setUserName("石头");
        user.setPassWord("123456");
        user.setRealName("石破天");
        int id = userService.insert(user);
        Assert.assertEquals("1", id);
    }

    @Test
    void selectList() {
        List<User> userList = userService.selectList();
        Assert.assertTrue(userList.size() >= 2);
        System.out.println(userList);
        userList.forEach(System.out::println);
    }
}