package com.mybatis.plus.mysql.sims.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

/**
 * @author : chenbo
 * @date : 2021-12-18
 */
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClassMapperTest {
    @Autowired
    private ClassMapper classMapper;

    @Test
    void findByClassName() {
    }

    @Test
    void findByClassNameOrCollegeId() {
        System.out.println(classMapper.findByClassNameOrCollegeId(null, "co"));
        System.out.println(classMapper.findByClassNameOrCollegeId("name", null));
        System.out.println(classMapper.findByClassNameOrCollegeId("name", "co"));
    }
}