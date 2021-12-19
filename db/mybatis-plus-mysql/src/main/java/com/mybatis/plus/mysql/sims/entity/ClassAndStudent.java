package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author chenbo
 * @since 2021-12-17
 */
@Data
@TableName("sims_class")
public class ClassAndStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("班级ID")
    private String classId;

    @ApiModelProperty("所在学院")
    private String collegeId;

    @ApiModelProperty("所属专业ID")
    private String majorId;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("班级人数")
    private Integer studentNumber;

    @ApiModelProperty("辅导员")
    private String adviser;

    @ApiModelProperty("成立时间")
    private LocalDateTime estabDate;

    @ApiModelProperty("学习年数")
    private Integer yearNumber;

    @ApiModelProperty("租户号")
    private String tenantId;

    @ApiModelProperty("乐观锁")
    private Integer revision;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;

    // private List<Student> students;
    private Student students;
}
