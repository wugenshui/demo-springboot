package com.chenbo.daomybatis.mapper;

import com.chenbo.daomybatis.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author : chenbo
 * @date : 2019/11/21
 */
@Repository
public interface UserMapper {
    User Sel(int id);
}
