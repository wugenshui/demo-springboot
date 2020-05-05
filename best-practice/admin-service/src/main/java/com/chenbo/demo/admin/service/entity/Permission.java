package com.chenbo.demo.admin.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author chenbo
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_permission")
@ApiModel(value="Permission对象", description="权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父权限")
    private Long parentId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限英文名称")
    private String enname;

    @ApiModelProperty(value = "授权路径")
    private String url;

    @ApiModelProperty(value = "备注")
    private String description;

    private LocalDateTime created;

    private LocalDateTime updated;


}
