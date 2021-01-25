package com.chenbo.demo.generate.db.doc.entity;

import lombok.Data;

/**
 * 数据库表字段信息
 *
 * @author : chenbo
 * @date : 2020-04-05
 */
@Data
public class TableFileds {

    /**
     * 字段名
     */
    private String field;

    /**
     * 类型
     */
    private String type;

    /**
     * 是否为空
     */
    private String Null;

    /**
     * 主键
     */
    private String key;

    /**
     * 字段说明
     */
    private String comment;

    /**
     * 默认值
     */
    private String Default;

    /**
     * 数据长度
     */
    private String length;

    /**
     * 数据长度
     */
    private String precision;

    /**
     * 小数位数
     */
    private String scale;

}
