package com.chenbo.demo.admin.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Data
@NoArgsConstructor
@ApiModel("用户")
public class UserVo {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "请输入用户名")
    private String username;

    @ApiModelProperty(value = "密码，加密存储")
    @Ignore
    private String password;

    @ApiModelProperty(value = "注册手机号")
    private String phone;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    private LocalDateTime created;

    private LocalDateTime updated;
}
