package com.wugenshui.security.service;

import com.wugenshui.security.entity.SysUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : chenbo
 * @date : 2023-10-23
 */
@Service
public class SecurityService implements UserDetailsService {
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getFirst();

        if (sysUser == null) {
            throw new UsernameNotFoundException("user is not found");
        }
        // return这么一长串是因为我的user实体类名字是User，与org.springframework.security.core.userdetails.User重名了
        return new User(
                username,
                sysUser.getPassword(),
                // 该参数暂时用不到，无意义，参数随便填
                AuthorityUtils.commaSeparatedStringToAuthorityList("")
        );
    }
}
