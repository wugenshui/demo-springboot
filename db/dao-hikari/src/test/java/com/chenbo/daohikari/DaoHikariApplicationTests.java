package com.chenbo.daohikari;

import com.chenbo.daohikari.domain.User;
import com.chenbo.daohikari.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class DaoHikariApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        PageHelper.startPage(1, 5);
        PageInfo<User> userPageInfo = new PageInfo<User>(userMapper.selectAll());
        System.out.println("userPageInfo = " + userPageInfo);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(123213L);
        user.setName("");
        user.setAge(0);
        user.setEmail("");
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setVersion(0);
        user.setDeleted(0);

        userMapper.insert(user);
    }
}
