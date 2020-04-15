package com.chenbo.daomongodb.dao;

import com.chenbo.daomongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {
}
