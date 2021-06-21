package com.chenbo.daomybatisplus.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chenbo.daomybatisplus.auth.enums.TypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

@Data
@TableName(autoResultMap = true)
public class EnumInfo {
    @TableField(jdbcType = JdbcType.INTEGER)
    @ApiModelProperty(value = "类型")
    private TypeEnum type;
}
