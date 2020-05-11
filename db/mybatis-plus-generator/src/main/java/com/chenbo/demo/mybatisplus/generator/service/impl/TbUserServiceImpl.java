package com.chenbo.demo.mybatisplus.generator.service.impl;

import com.chenbo.demo.mybatisplus.generator.entity.TbUser;
import com.chenbo.demo.mybatisplus.generator.mapper.TbUserMapper;
import com.chenbo.demo.mybatisplus.generator.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenbo
 * @since 2020-05-11
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
