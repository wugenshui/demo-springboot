package com.chenbo.daomybatis.service;

import com.chenbo.daomybatis.entity.User;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
public interface UserService {

    /**
     * 根据id查询用户
     *
     * @param id 主键
     * @return
     */
    User selectById(Long id);

    /**
     * 插入用户
     *
     * @param user 用户实体
     * @return 插入行数
     */
    int insert(User user);
}
