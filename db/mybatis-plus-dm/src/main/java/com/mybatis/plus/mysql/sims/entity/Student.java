package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author chenbo
 * @since 2021-12-18
 */
@Data
@TableName("sims_student")
@ApiModel(value = "Student对象", description = "学生")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所在学院ID")
    private String collegeId;

    @ApiModelProperty("所在班级ID")
    private String classId;

    @ApiModelProperty("学生ID")
    private String studentId;

    @ApiModelProperty("学生姓名")
    private String studentName;

}
