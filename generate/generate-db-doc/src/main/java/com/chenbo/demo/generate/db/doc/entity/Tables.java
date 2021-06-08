package com.github.wugenshui.generate.db.doc.entity;

import lombok.Data;

/**
 * @author : chenbo
 * @date : 2020-04-05
 */
@Data
public class Tables {

    /**
     * 表名
     */
    private String name;

    /**
     * 表注释
     */
    private String comment;
}
