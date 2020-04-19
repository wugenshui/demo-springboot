package com.chenbo.demo.spring.security.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}