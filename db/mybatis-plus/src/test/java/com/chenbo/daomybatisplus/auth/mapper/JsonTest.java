package com.chenbo.daomybatisplus.auth.mapper;

import com.chenbo.daomybatisplus.auth.entity.JsonInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonTest {
    @Autowired
    private JsonMapper jsonMapper;

    @Test
    public void test() {
//        JsonInfo jsonInfo = new JsonInfo();
//        jsonInfo.setExtraObject(new PGobject());
//        int insert = jsonMapper.insert(jsonInfo);
//        System.out.println("insert = " + insert);
        List<JsonInfo> tests = jsonMapper.selectList(null);
        System.out.println(tests);
    }
}
