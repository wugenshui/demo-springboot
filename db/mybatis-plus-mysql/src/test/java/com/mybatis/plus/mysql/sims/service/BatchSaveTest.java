package com.mybatis.plus.mysql.sims.service;

import cn.hutool.core.date.StopWatch;
import com.mybatis.plus.mysql.sims.entity.Class;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2023-11-19
 */
@SpringBootTest
public class BatchSaveTest {
    @Autowired
    private IClassService classService;

    @Test
    void insertTest() {
        StopWatch stopWatch = new StopWatch();
        int size = 5;
        stopWatch.start("初始化数据:" + size);
        List<Class> classes = new ArrayList<>(5000);
        for (int i = 0; i < size; i++) {
            Class clazz = new Class();
            clazz.setClassId(i + "");
            clazz.setClassName("高一" + i + "班");
            classes.add(clazz);
        }
        stopWatch.stop();

        stopWatch.start("批量插入");
        // saveBatch 是简写，但是实际上数据仍是一条一条插入的
        boolean state = classService.saveBatch(classes);
        stopWatch.stop();
        System.out.println("save state:" + state);
        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
    }
}
