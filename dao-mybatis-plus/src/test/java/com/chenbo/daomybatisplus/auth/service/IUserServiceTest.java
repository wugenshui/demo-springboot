package com.chenbo.daomybatisplus.auth.service;

import com.chenbo.daomybatisplus.auth.entity.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : chenbo
 * @date : 2019/12/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class IUserServiceTest {
    @Autowired
    IUserService userService;

    @Test
    public void getByIdTest() {
        User user = userService.getById(1088248166370832385L);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("胡龙飞");
        user.setAge(18);
        boolean state = userService.save(user);
        System.out.printf("插入状态：%s 插入id：%s", state, user.getId());
        Assert.assertNotNull(user);
    }

    @Test
    public void updateByIdTest() {
        User user = new User();
        user.setVersion(1);
        user.setId(1094590409767661570L);
        user.setEmail("soft@gmail.com");
        boolean state = userService.updateById(user);
        Assert.assertTrue(state);
    }

    @Test
    public void removeByIdTest() {
        boolean state = userService.removeById(1094590409767661570L);
        Assert.assertTrue(state);
    }

    @Test
    public void updateDeletedTest() {
        int rows = userService.updateDeleted(1094590409767661570L);
        Assert.assertEquals(1, rows);
    }

}