package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 教师
 * </p>
 *
 * @author chenbo
 * @since 2021-12-19
 */
@TableName("sims_teacher")
@ApiModel(value = "Teacher对象", description = "教师")
@Data
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所在学院ID")
    private String collegeId;

    @ApiModelProperty("教师ID")
    private String teacherId;

    @ApiModelProperty("姓名")
    private String teacherName;

}
