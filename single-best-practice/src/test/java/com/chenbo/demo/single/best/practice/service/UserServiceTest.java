package com.chenbo.demo.single.best.practice.service;


import com.chenbo.demo.single.best.practice.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-12-25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void test() {
        List<User> list = userService.list();
        System.out.println("list = " + list);
    }
}
