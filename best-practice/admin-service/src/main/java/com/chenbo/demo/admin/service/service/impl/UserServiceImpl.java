package com.chenbo.demo.admin.service.service.impl;

import com.chenbo.demo.admin.service.entity.User;
import com.chenbo.demo.admin.service.mapper.UserMapper;
import com.chenbo.demo.admin.service.service.IUserService;
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
