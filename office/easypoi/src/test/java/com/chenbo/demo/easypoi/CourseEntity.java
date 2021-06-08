package com.github.wugenshui.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2020-03-07
 */
@ExcelTarget("courseEntity")
@Data
public class CourseEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 课程名称
     */
    @Excel(name = "课程名称", orderNum = "1", needMerge = true)
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别", orderNum = "1", needMerge = true)
    private String sex;
    /**
     * 老师主键
     */
//    @ExcelEntity(id = "yuwen")
//    @ExcelVerify()
//    private TeacherEntity teacher;
//    /**
//     * 老师主键
//     */
//    @ExcelEntity(id = "shuxue")
//    private TeacherEntity shuxueteacher;
//
//    @ExcelCollection(name = "选课学生", orderNum = "4")
//    private List<StudentEntity> students;
}