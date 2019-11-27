package com.chenbo.daomybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2019/11/27
 */
@Data
public class User {
    @TableId("id")
    private Integer id;

    @TableField("userName")
    private String userName;

    @TableField("passWord")
    private String passWord;

    @TableField("realName")
    private String realName;
}