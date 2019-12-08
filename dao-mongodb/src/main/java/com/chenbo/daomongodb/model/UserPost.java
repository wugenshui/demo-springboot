package com.chenbo.daomongodb.model;

import lombok.Data;

/**
 * 用户岗位
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Data
public class UserPost {
    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位等级
     */
    private Integer level;
}
