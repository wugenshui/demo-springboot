package com.chenbo.daomybatisplus.auth.service;

import com.chenbo.daomybatisplus.auth.entity.User;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2019/12/1
 */
@SpringBootTest
@Transactional // 支持事物，@SpringBootTest 事物默认自动回滚
@Rollback // 事务自动回滚，不自动回滚@Rollback(false)
public class IUserServiceTest {
    @Autowired
    IUserService userService;

    @Test
    public void listTest() {
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }

    @Test
    public void getByIdTest() {
        User user = userService.getById(1088248166370832385L);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    public void getOneTest() {
        // 第二个参数可以隐藏异常
        val one = userService.getOne(null, false);
        System.out.println("one = " + one);
        Assertions.assertNotNull(one);
    }

    @Test
    public void getByName() {
        User user = userService.getByName("王天风");
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("胡龙飞");
        user.setAge(18);
        boolean state = userService.save(user);
        System.out.printf("插入状态：%s 插入id：%s", state, user.getId());
        Assertions.assertNotNull(user);
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
        Assertions.assertTrue(state);

        int rows = userService.resumeVersion(1094590409767661570L);
        Assertions.assertEquals(1, rows);
    }

    @Test
    public void updateByNameTest() {
        User user = new User();
        user.setVersion(1);
        user.setId(1094590409767661570L);
        user.setEmail("soft@gmail.com");
        boolean updateState = userService.updateByName("胡龙飞", user);
        Assertions.assertTrue(updateState);
    }

    /**
     * 逻辑删除并恢复
     */
    @Test
    public void removeByIdTest() {
        boolean state = userService.removeById(1094590409767661570L);
        Assertions.assertTrue(state);

        int rows = userService.resumeDelete(1094590409767661570L);
        Assertions.assertEquals(1, rows);
    }
}