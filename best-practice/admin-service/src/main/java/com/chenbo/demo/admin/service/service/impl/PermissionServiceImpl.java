package com.chenbo.demo.admin.service.service.impl;

import com.chenbo.demo.admin.service.entity.Permission;
import com.chenbo.demo.admin.service.mapper.PermissionMapper;
import com.chenbo.demo.admin.service.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author chenbo
 * @since 2020-05-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
