package com.chenbo.daomybatisplus.auth.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapperTest {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {
        com.chenbo.daomybatisplus.auth.entity.Test model = new com.chenbo.daomybatisplus.auth.entity.Test();
        model.setA(new String[]{"1", "3", "5"});
        int insert = testMapper.insert(model);
        System.out.println("insert = " + insert);
        List<com.chenbo.daomybatisplus.auth.entity.Test> tests = testMapper.selectList(null);
        System.out.println(tests);
    }
}
