package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author chenbo
 * @since 2021-12-17
 */
@TableName("sims_class")
@ApiModel(value = "Class对象", description = "班级")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所在学院")
    private String collegeId;

    @ApiModelProperty("所属专业ID")
    private String majorId;

    @ApiModelProperty("班级ID")
    private String classId;

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

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }
    public LocalDateTime getEstabDate() {
        return estabDate;
    }

    public void setEstabDate(LocalDateTime estabDate) {
        this.estabDate = estabDate;
    }
    public Integer getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Class{" +
            "collegeId=" + collegeId +
            ", majorId=" + majorId +
            ", classId=" + classId +
            ", className=" + className +
            ", studentNumber=" + studentNumber +
            ", adviser=" + adviser +
            ", estabDate=" + estabDate +
            ", yearNumber=" + yearNumber +
            ", tenantId=" + tenantId +
            ", revision=" + revision +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", updatedBy=" + updatedBy +
            ", updatedTime=" + updatedTime +
        "}";
    }
}
