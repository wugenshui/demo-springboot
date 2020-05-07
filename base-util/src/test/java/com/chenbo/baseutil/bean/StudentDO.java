package com.chenbo.baseutil.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO {
    // 身份ID
    private Long id;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    // 电话
    private String mobile;
}
