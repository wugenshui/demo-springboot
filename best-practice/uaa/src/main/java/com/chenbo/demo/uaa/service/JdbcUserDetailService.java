package com.chenbo.demo.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2020-04-26
 */
@Service
public class JdbcUserDetailService implements UserDetailsService {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = User.withUsername(username).password(passwordEncoder.encode(username)).authorities(username).build();
        return userDetails;
    }
}
