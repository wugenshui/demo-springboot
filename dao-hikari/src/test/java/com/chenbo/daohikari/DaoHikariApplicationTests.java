package com.chenbo.daohikari;

import com.chenbo.daohikari.domain.User;
import com.chenbo.daohikari.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoHikariApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }
}
