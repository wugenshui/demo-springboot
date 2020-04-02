package com.chenbo.best.practice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Data
@Builder
@ApiModel("用户")
public class UserVo {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @Max(value = 120, message = "年龄不能大于120")
    @Min(value = 0, message = "年龄不能小于0")
    @NotEmpty(message = "请输入用户名")
    @ApiModelProperty("用户年龄")
    private Integer age;
}
