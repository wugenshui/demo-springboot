package com.chenbo.demo.redis.cache.mapper;

import com.chenbo.demo.redis.cache.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();
}