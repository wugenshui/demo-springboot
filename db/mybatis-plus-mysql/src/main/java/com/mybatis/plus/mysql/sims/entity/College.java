package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 学院
 * </p>
 *
 * @author chenbo
 * @since 2021-12-19
 */
@TableName("sims_college")
@ApiModel(value = "College对象", description = "学院")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学院ID")
    private String collegeId;

    @ApiModelProperty("学院名称")
    private String collegeName;

    @ApiModelProperty("学院简称")
    private String shortName;

    @ApiModelProperty("学院介绍")
    private String intro;

    @ApiModelProperty("专业个数")
    private Integer professionNumber;

    @ApiModelProperty("学生人数")
    private Integer studentNumber;

    @ApiModelProperty("院长")
    private String president;

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
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    public Integer getProfessionNumber() {
        return professionNumber;
    }

    public void setProfessionNumber(Integer professionNumber) {
        this.professionNumber = professionNumber;
    }
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
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
        return "College{" +
            "collegeId=" + collegeId +
            ", collegeName=" + collegeName +
            ", shortName=" + shortName +
            ", intro=" + intro +
            ", professionNumber=" + professionNumber +
            ", studentNumber=" + studentNumber +
            ", president=" + president +
            ", tenantId=" + tenantId +
            ", revision=" + revision +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", updatedBy=" + updatedBy +
            ", updatedTime=" + updatedTime +
        "}";
    }
}
