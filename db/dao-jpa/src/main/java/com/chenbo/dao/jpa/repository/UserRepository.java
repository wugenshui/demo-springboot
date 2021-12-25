package com.chenbo.dao.jpa.repository;

import com.chenbo.dao.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-02-12
 */
@Repository
public interface UserRepository extends JpaRepository<JpaUser, Long> {
    /**
     * 查找同名用户
     *
     * @param name
     * @return
     */
    List<JpaUser> findByName(String name);

    /**
     * 查找指定用户
     *
     * @param name
     * @return
     */
    JpaUser getOneByName(String name);
}
