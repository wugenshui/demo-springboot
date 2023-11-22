package com.mybatis.plus.mysql.sims.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatis.plus.mysql.sims.entity.Class;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2021-12-17
 */
@SpringBootTest
@Slf4j
class IClassServiceTest {
    @Autowired
    private IClassService classService;

    @Test
    void apiTest() {
        String classId = "my_id";
        String className = "高一三班";

        Class clazz = new Class();
        clazz.setClassId(classId);
        clazz.setClassName(className);

        log.info("插入一条记录：classService.save(clazz)");
        classService.save(clazz);
        Assertions.assertEquals(className, clazz.getClassName());

        log.info("先查询是否存在然后在插入：classService.saveOrUpdate(clazz)");
        classService.saveOrUpdate(clazz);
        Assertions.assertEquals(className, clazz.getClassName());

        log.info("先通过updateWrapper更新，若更新不成功则执行saveOrUpdate(clazz)：classService.saveOrUpdate(clazz, updateWrapper)");
        LambdaQueryWrapper<Class> updateWrapper = new LambdaQueryWrapper();
        updateWrapper.eq(Class::getClassName, className);
        classService.saveOrUpdate(clazz, updateWrapper);
        Assertions.assertEquals(className, clazz.getClassName());

        log.info("classService.removeByClassId(clazz.getClassId())");
        Assertions.assertTrue(classService.removeByClassId(clazz.getClassId()));
    }

    @Test
    void simpleQueryTest() {
        List<Class> list = classService.list();
        log.info(list.toString());

        List<Map<String, Object>> maps = classService.listMaps();
        log.info(maps.toString());
    }

    @Test
    void queryTest() {
        // WHERE (class_id = '123')
        LambdaQueryWrapper<Class> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(true, Class::getClassId, "123");
        classService.list(lambdaQueryWrapper);

        // WHERE (class_id = '123' AND class_id = '456')
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", "123");
        queryWrapper.lambda().eq(Class::getClassId, "456");
        classService.list(queryWrapper);

        // WHERE ((class_id = 'lambdaClassId' AND class_name = '张三') OR class_id = '123')
        LambdaQueryWrapper<Class> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.and(o -> {
            o.eq(Class::getClassId, "lambdaClassId").eq(Class::getClassName, "张三");
        }).or().eq(Class::getClassId, "123");
        classService.list(lambdaQueryWrapper1);
    }
}