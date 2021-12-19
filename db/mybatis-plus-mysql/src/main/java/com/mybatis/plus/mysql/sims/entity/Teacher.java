package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("所在学院ID")
    private String collegeId;

    @ApiModelProperty("教师ID")
    private String teacherId;

    @ApiModelProperty("姓名")
    private String teacherName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("出生日期")
    private LocalDateTime birth;

    @ApiModelProperty("毕业院校")
    private String graduateInstitution;

    @ApiModelProperty("从业年限")
    private Integer practiceYears;

    @ApiModelProperty("政治面貌")
    private String political;

    @ApiModelProperty("婚姻状况")
    private String marital;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("介绍")
    private String intro;

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
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }
    public String getGraduateInstitution() {
        return graduateInstitution;
    }

    public void setGraduateInstitution(String graduateInstitution) {
        this.graduateInstitution = graduateInstitution;
    }
    public Integer getPracticeYears() {
        return practiceYears;
    }

    public void setPracticeYears(Integer practiceYears) {
        this.practiceYears = practiceYears;
    }
    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }
    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
        return "Teacher{" +
            "collegeId=" + collegeId +
            ", teacherId=" + teacherId +
            ", teacherName=" + teacherName +
            ", gender=" + gender +
            ", birth=" + birth +
            ", graduateInstitution=" + graduateInstitution +
            ", practiceYears=" + practiceYears +
            ", political=" + political +
            ", marital=" + marital +
            ", avatar=" + avatar +
            ", intro=" + intro +
            ", tenantId=" + tenantId +
            ", revision=" + revision +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", updatedBy=" + updatedBy +
            ", updatedTime=" + updatedTime +
        "}";
    }
}
