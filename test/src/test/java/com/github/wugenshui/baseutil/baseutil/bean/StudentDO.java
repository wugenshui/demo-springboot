package com.github.wugenshui.baseutil.baseutil.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO {
    /**
     * 身份ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * transient属性
     */
    private transient String tempName;
}
