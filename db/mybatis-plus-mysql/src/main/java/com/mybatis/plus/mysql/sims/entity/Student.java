package com.mybatis.plus.mysql.sims.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 学生
 * </p>
 *
 * @author chenbo
 * @since 2021-12-18
 */
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

    @ApiModelProperty("英文名")
    private String engName;

    @ApiModelProperty("身份证号")
    private String idCardNo;

    @ApiModelProperty("手机号")
    private String mobilePhone;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("月薪")
    private BigDecimal monthlySalary;

    @ApiModelProperty("出生日期")
    private LocalDateTime birth;

    @ApiModelProperty("头像")
    private Integer avatar;

    @ApiModelProperty("身高")
    private Integer height;

    @ApiModelProperty("体重")
    private Integer weight;

    @ApiModelProperty("名族")
    private String nation;

    @ApiModelProperty("政治面貌")
    private String political;

    @ApiModelProperty("婚姻状况")
    private String marital;

    @ApiModelProperty("籍贯（省）")
    private String domicilePlaceProvince;

    @ApiModelProperty("籍贯（市）")
    private String domicilePlaceCity;

    @ApiModelProperty("户籍地址")
    private String domicilePlaceAddress;

    @ApiModelProperty("爱好")
    private String hobby;

    @ApiModelProperty("简要介绍")
    private String intro;

    @ApiModelProperty("居住地址")
    private String presentAddress;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("入学日期")
    private LocalDateTime entryDate;

    @ApiModelProperty("状态")
    private String status;

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
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }
    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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
    public String getDomicilePlaceProvince() {
        return domicilePlaceProvince;
    }

    public void setDomicilePlaceProvince(String domicilePlaceProvince) {
        this.domicilePlaceProvince = domicilePlaceProvince;
    }
    public String getDomicilePlaceCity() {
        return domicilePlaceCity;
    }

    public void setDomicilePlaceCity(String domicilePlaceCity) {
        this.domicilePlaceCity = domicilePlaceCity;
    }
    public String getDomicilePlaceAddress() {
        return domicilePlaceAddress;
    }

    public void setDomicilePlaceAddress(String domicilePlaceAddress) {
        this.domicilePlaceAddress = domicilePlaceAddress;
    }
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Student{" +
            "collegeId=" + collegeId +
            ", classId=" + classId +
            ", studentId=" + studentId +
            ", studentName=" + studentName +
            ", engName=" + engName +
            ", idCardNo=" + idCardNo +
            ", mobilePhone=" + mobilePhone +
            ", gender=" + gender +
            ", monthlySalary=" + monthlySalary +
            ", birth=" + birth +
            ", avatar=" + avatar +
            ", height=" + height +
            ", weight=" + weight +
            ", nation=" + nation +
            ", political=" + political +
            ", marital=" + marital +
            ", domicilePlaceProvince=" + domicilePlaceProvince +
            ", domicilePlaceCity=" + domicilePlaceCity +
            ", domicilePlaceAddress=" + domicilePlaceAddress +
            ", hobby=" + hobby +
            ", intro=" + intro +
            ", presentAddress=" + presentAddress +
            ", email=" + email +
            ", entryDate=" + entryDate +
            ", status=" + status +
            ", tenantId=" + tenantId +
            ", revision=" + revision +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", updatedBy=" + updatedBy +
            ", updatedTime=" + updatedTime +
        "}";
    }
}
