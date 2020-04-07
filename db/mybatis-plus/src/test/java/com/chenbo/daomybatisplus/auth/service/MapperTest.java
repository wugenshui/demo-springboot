package com.chenbo.daomybatisplus.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenbo.daomybatisplus.auth.entity.User;
import com.chenbo.daomybatisplus.auth.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 支持事物，@SpringBootTest 事物默认自动回滚
@Rollback // 事务自动回滚，不自动回滚@Rollback(false)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectListTest() {
        List<User> user = userMapper.selectList(null);
        System.out.println("user = " + user);
    }

    @Test
    public void Test() {
        Page<User> page = new Page<>(2, 3);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 10);

        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("查询总数 = " + userPage.getTotal());
        System.out.println("查询结果 = " + userPage.getRecords());
    }
}
