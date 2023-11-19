package com.mybatis.plus.mysql.sims.service;

import cn.hutool.core.date.StopWatch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        int size = 50000;
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
        // 优化1. 批量插入表主键 @TableId(type = IdType.NONE)             5万条12.508s
        // 优化2. YAML配置文件增加配置 mybatis-plus.executorType: BATCH   5万条12.352s
        // 优化3. 数据库连接字符串增加参数 &rewriteBatchedStatements=true 5万条6.470s
        boolean state = classService.saveBatch(classes);
        stopWatch.stop();
        System.out.println("save state:" + state);

        stopWatch.start("查询数据");
        QueryWrapper queryWrapper = new QueryWrapper();
        List list = classService.list(queryWrapper);
        System.out.println("query count:" + list.size());
        stopWatch.stop();

        stopWatch.start("删除数据");
        classService.remove(queryWrapper);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
    }
}
