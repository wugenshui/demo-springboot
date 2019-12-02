package com.chenbo.daomybatisplus.auth.service;

import com.chenbo.daomybatisplus.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
public interface IUserService extends IService<User> {
    /**
     * 恢复删除标记
     *
     * @param id 主键
     * @return 影响行数
     */
    int resumeDelete(Long id);


    /**
     * 版本恢复
     *
     * @param id 主键
     * @return 影响行数
     */
    int resumeVersion(Long id);
}
