package com.chenbo.daomybatisplus.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenbo.daomybatisplus.auth.entity.User;
import com.chenbo.daomybatisplus.auth.mapper.UserMapper;
import com.chenbo.daomybatisplus.auth.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int resumeDelete(Long id) {
        return baseMapper.resumeDelete(id);
    }

    @Override
    public int resumeVersion(Long id) {
        return baseMapper.resumeVersion(id);
    }
}
