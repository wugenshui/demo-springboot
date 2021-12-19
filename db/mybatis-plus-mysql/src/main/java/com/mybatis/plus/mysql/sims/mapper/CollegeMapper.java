package com.mybatis.plus.mysql.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.plus.mysql.sims.entity.College;
import com.mybatis.plus.mysql.sims.entity.CollegeAndStudent;
import com.mybatis.plus.mysql.sims.entity.CollegeAndTeacher;

import java.util.List;

/**
 * <p>
 * 学院 Mapper 接口
 * </p>
 *
 * @author chenbo
 * @since 2021-12-19
 */
public interface CollegeMapper extends BaseMapper<College> {
    List<CollegeAndTeacher> findCollegeAndTeacher();
    List<CollegeAndStudent> findCollegeAndStudent();
}
