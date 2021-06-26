package com.chenbo.daomybatisplus.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chenbo.daomybatisplus.handle.JsonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.postgresql.util.PGobject;

@Data
@TableName(autoResultMap = true)
public class JsonInfo {
    private static final long serialVersionUID = 1L;

    @TableField(typeHandler = JsonTypeHandler.class)
    @ApiModelProperty(value = "额外对象")
    private PGobject extraObject;
}

