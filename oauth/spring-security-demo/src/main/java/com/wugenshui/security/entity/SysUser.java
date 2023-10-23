package com.wugenshui.security.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@Data
@Builder
public class SysUser {
    private String id;
    private String username;
    private String password;
    private String mobile;
}
