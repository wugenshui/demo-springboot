package com.mybatis.plus.mysql.sims.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mybatis.plus.mysql.sims.entity.Class;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IClassServiceTest {
    @Autowired
    private IClassService classService;

    @Test
    public void apiTest() {
        String classId = "my_id";
        String className = "高一三班";

        Class clazz = new Class();
        clazz.setClassId(classId);
        clazz.setClassName(className);

        log.info("插入一条记录：classService.save(clazz)");
        classService.save(clazz);
        Assert.assertEquals(className, clazz.getClassName());

        log.info("先查询是否存在然后在插入：classService.saveOrUpdate(clazz)");
        classService.saveOrUpdate(clazz);
        Assert.assertEquals(className, clazz.getClassName());

        log.info("先通过updateWrapper更新，若更新不成功则执行saveOrUpdate(clazz)：classService.saveOrUpdate(clazz, updateWrapper)");
        LambdaQueryWrapper<Class> updateWrapper = new LambdaQueryWrapper();
        updateWrapper.eq(Class::getClassName, className);
        classService.saveOrUpdate(clazz, updateWrapper);
        Assert.assertEquals(className, clazz.getClassName());

        log.info("classService.removeByClassId(clazz.getClassId())");
        Assert.assertTrue(classService.removeByClassId(clazz.getClassId()));
    }
}