package com.chenbo.daomongodb.service;

import com.chenbo.daomongodb.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTemplateTest {
    @Autowired
    private UserTemplateService userTemplateService;

    @Test
    public void insert() {
        User user = userTemplateService.insert();
        Assert.assertNotNull(user);
        System.out.println("新增用户" + user);

        user.setPhone("10086");
        UpdateResult updateResult = userTemplateService.update(user);
        Assert.assertNotNull(user);
        System.out.println("修改状态" + updateResult);
        System.out.println("修改用户" + user);
    }

    @Test
    public void findAll() {
        List<User> users = userTemplateService.findAll();
        Assert.assertTrue(users.size() > 0);
        System.out.println("查询所有" + users);

        for (User user : users) {
            User queryUser = userTemplateService.findById(user.getId());
            Assert.assertNotNull(queryUser);
            System.out.println("查询单个" + queryUser);
        }
    }

    @Test
    public void deleteTest() {
        List<User> users = userTemplateService.findAll();
        Assert.assertTrue(users.size() > 0);
        System.out.println("查询所有" + users);

        for (User user : users) {
            DeleteResult deleteResult = userTemplateService.delete(user);
            System.out.println("deleteResult = " + deleteResult);
        }
    }

    @Test
    public void find() {
        HashMap hashMap = userTemplateService.find();
        System.out.println("hashMap = " + hashMap);
    }
}