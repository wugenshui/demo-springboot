package com.chenbo.daomybatisplus.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.chenbo.daomybatisplus.config.GeometryTypeHandler;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2020-12-28
 */
@Data
public class Map {
    private int id;

    @TableField(typeHandler = GeometryTypeHandler.class)
    private String coordinate;
}
