package com.mybatis.plus.mysql.sims.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.mybatis.plus.mysql.sims.entity.CollegeAndStudent;
import com.mybatis.plus.mysql.sims.entity.CollegeAndTeacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2021-12-19
 */
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CollegeMapperTest {
    @Autowired
    private CollegeMapper collegeMapper;

    @Test
    void findCollegeAndTeacher() {
        // 关联的嵌套结果映射 （1:1对象自动填充）
        List<CollegeAndTeacher> collegeAndTeacher = collegeMapper.findCollegeAndTeacher();
        System.out.println(collegeAndTeacher);
    }

    @Test
    void find() {
        // 集合的嵌套结果映射 (1:n对象自动填充)
        List<CollegeAndStudent> collegeAndStudent = collegeMapper.findCollegeAndStudent();
        System.out.println(collegeAndStudent);
    }
}