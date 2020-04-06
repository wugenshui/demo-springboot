package com.chenbo.dao.jpa.repository;

import com.chenbo.dao.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-02-12
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 查找同名用户
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 查找指定用户
     *
     * @param name
     * @return
     */
    User getOneByName(String name);
}
