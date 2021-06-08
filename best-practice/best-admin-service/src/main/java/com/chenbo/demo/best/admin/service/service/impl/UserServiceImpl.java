package com.github.wugenshui.best.admin.service.service.impl;

import com.github.wugenshui.best.admin.service.entity.User;
import com.github.wugenshui.best.admin.service.mapper.UserMapper;
import com.github.wugenshui.best.admin.service.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenbo
 * @since 2020-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
