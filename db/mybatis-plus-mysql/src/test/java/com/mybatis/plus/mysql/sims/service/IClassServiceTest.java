package com.mybatis.plus.mysql.sims.service;

import com.mybatis.plus.mysql.sims.entity.Class;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : chenbo
 * @date : 2021-12-17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IClassServiceTest {
    @Autowired
    private IClassService classService;

    @Test
    public void apiTest() {
        Class clazz = new Class();
        clazz.setClassId("gy");
        clazz.setClassName("高一三班");

        classService.save(clazz);
        Assert.assertEquals("高一三班", clazz.getClassName());

        Assert.assertTrue(classService.removeByClassId(clazz.getClassId()));
    }
}