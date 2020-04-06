package com.chenbo.daomybatis.mapper;

import com.chenbo.daomybatis.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Repository
public interface UserMapper {
    /**
     * 根据id查询用户
     *
     * @param id 主键
     * @return 用户实体
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
