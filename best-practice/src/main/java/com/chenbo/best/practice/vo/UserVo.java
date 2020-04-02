package com.chenbo.best.practice.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author : chenbo
 * @date : 2020-04-02
 */
@Data
@Builder
public class UserVo {
    private Integer id;

    private String name;

    @Max(value = 120, message = "年龄不能大于120")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;
}
