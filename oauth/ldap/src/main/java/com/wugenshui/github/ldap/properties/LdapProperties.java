package com.wugenshui.github.ldap.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : chenbo
 * @date : 2024-08-13
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ldap")
public class LdapProperties {

    /**
     * 是否启用ldap登录功能
     */
    private String enable;

    /**
     * ldap服务器地址
     */
    private String host;

    /**
     * ldap登录用户
     */
    private String username;

    /**
     * 机构路径
     */
    private String ouPath;

    /**
     * 用户过滤条件
     */
    private String userFilter;

    /**
     * ldap登录密码
     */
    private String password;
}
