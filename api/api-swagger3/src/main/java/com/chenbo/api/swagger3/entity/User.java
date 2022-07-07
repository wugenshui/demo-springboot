package com.chenbo.api.swagger3.entity;

import com.chenbo.api.swagger3.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author : chenbo
 * @date : 2020-02-08
 */
@ApiModel("用户")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户名")
    @NotEmpty(message = "请输入用户名!")
    private String name;

    @ApiModelProperty("密码")
    @NotEmpty(message = "请输入用户密码!")
    private String password;

    @ApiModelProperty("用户年龄")
    @Max(value = 150, message = "用户年龄应小于150岁!")
    @Min(value = 0, message = "用户年龄应大于0岁!")
    private Integer age;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("手机号码")
    @IsMobile
    private String mobile;

    @ApiModelProperty("爱好")
    private String hobby;
}
