package com.wugenshui.security.service;

import com.wugenshui.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SysUser getFirst() {
        return new SysUser(passwordEncoder.encode("1"));
    }
}
