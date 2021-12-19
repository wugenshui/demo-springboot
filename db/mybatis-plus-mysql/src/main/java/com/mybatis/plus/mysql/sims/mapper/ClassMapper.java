package com.mybatis.plus.mysql.sims.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.plus.mysql.sims.entity.Class;
import com.mybatis.plus.mysql.sims.entity.ClassAndStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 班级 Mapper 接口
 * </p>
 *
 * @author chenbo
 * @since 2021-12-17
 */
public interface ClassMapper extends BaseMapper<Class> {
    List<ClassAndStudent> findClassAndStudent();

    List<Class> findByClassName(@Param("className") String className);

    List<Class> findByClassNameOrCollegeId(@Param("className") String className, @Param("collegeId") String collegeId);
}
