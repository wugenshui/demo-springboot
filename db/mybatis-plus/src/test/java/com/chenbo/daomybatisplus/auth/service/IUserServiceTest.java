package com.chenbo.daomybatisplus.auth.service;

import com.chenbo.daomybatisplus.auth.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : chenbo
 * @date : 2019/12/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 支持事物，@SpringBootTest 事物默认自动回滚
@Rollback // 事务自动回滚，不自动回滚@Rollback(false)
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

    /**
     * 更新数据并恢复
     */
    @Test
    public void updateByIdTest() {
        User user = new User();
        user.setVersion(1);
        user.setId(1094590409767661570L);
        user.setEmail("soft@gmail.com");
        boolean state = userService.updateById(user);
        Assert.assertTrue(state);

        int rows = userService.resumeVersion(1094590409767661570L);
        Assert.assertEquals(1, rows);
    }

    /**
     * 逻辑删除并恢复
     */
    @Test
    public void removeByIdTest() {
        boolean state = userService.removeById(1094590409767661570L);
        Assert.assertTrue(state);

        int rows = userService.resumeDelete(1094590409767661570L);
        Assert.assertEquals(1, rows);
    }
}