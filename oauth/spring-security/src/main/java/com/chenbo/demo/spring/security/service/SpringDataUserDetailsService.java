package com.chenbo.demo.spring.security.service;

import com.chenbo.demo.spring.security.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-04-19
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    /**
     * 根据 账号查询用户信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 将来连接数据库根据账号查询用户信息
        UserDto userDto = getUserByUsername(username);
        if (userDto == null) {
            // 如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        // 根据用户的id查询用户的权限
        List<String> permissions = findPermissionsByUserId(userDto.getId());
        // 将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }

    private UserDto getUserByUsername(String username) {
        if ("admin".equals(username)) {
            return UserDto.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .build();
        } else {
            return UserDto.builder()
                    .username("user")
                    .password(new BCryptPasswordEncoder().encode("user"))
                    .build();
        }
    }

    private List<String> findPermissionsByUserId(String id) {
        return Arrays.asList("f1", "f2", "f3");
    }

}
