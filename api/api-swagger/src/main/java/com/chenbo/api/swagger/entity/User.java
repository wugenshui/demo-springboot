package com.chenbo.api.swagger.entity;

import com.chenbo.api.swagger.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author : chenbo
 * @date : 2020-02-08
 */
@ApiModel("用户")
@Data
public class User {
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
    private LocalDateTime createTime;

    @ApiModelProperty("手机号码")
    @IsMobile
    private String mobile;

    @ApiModelProperty("爱好")
    private String hobby;
}
