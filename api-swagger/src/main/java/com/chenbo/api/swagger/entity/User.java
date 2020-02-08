package com.chenbo.api.swagger.entity;

import com.chenbo.api.swagger.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author : chenbo
 * @date : 2020-02-08
 */
@Data
public class User {
    private Long id;

    @NotEmpty(message = "请输入用户名!")
    private String name;

    @Max(value = 150, message = "年龄应小于150岁!")
    @Min(value = 0, message = "年龄应大于0岁!")
    private Integer age;

    private LocalDateTime createTime;

    @IsMobile
    private String mobile;
}
