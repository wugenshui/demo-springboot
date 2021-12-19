package com.mybatis.plus.mysql.sims.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 学院和院老师
 * </p>
 *
 * @author chenbo
 * @since 2021-12-19
 */
@Data
public class CollegeAndTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学院ID")
    private String collegeId;

    @ApiModelProperty("学院名称")
    private String collegeName;

    private Teacher teacher;
}
