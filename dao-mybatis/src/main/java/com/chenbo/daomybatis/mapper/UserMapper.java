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
     * 根据id查询实体
    * @param id
     * @return
     */
    User Sel(int id);
}
