package com.chenbo.daomybatisplus.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chenbo.daomybatisplus.handle.ArrayTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
@Data
@TableName(autoResultMap = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "a", jdbcType = JdbcType.ARRAY, typeHandler = ArrayTypeHandler.class)
    @ApiModelProperty(value = "主键")
    private String[] a;

}
