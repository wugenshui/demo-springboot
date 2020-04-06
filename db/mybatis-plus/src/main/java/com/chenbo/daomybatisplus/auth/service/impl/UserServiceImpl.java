package com.chenbo.daomybatisplus.auth.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenbo.daomybatisplus.auth.entity.User;
import com.chenbo.daomybatisplus.auth.mapper.UserMapper;
import com.chenbo.daomybatisplus.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public int resumeDelete(Long id) {
        return baseMapper.resumeDelete(id);
    }

    @Override
    public int resumeVersion(Long id) {
        return baseMapper.resumeVersion(id);
    }

    @Override
    public User getByName(String name) {
        LambdaQueryChainWrapper<User> lambdaQuery = new LambdaQueryChainWrapper<>(userMapper);
        User user = lambdaQuery().eq(User::getName, name).one();
        return user;
    }

    @Override
    public boolean updateByName(String name, User user) {
        LambdaUpdateChainWrapper<User> lambdaUpdate = new LambdaUpdateChainWrapper<>(userMapper);
        return lambdaUpdate().eq(User::getName, name).update(user);
    }
}
