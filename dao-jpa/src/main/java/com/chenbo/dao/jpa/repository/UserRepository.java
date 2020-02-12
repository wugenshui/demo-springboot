package com.chenbo.dao.jpa.repository;

import com.chenbo.dao.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : chenbo
 * @date : 2020-02-12
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findByReader(String reader);
}
