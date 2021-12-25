package com.chenbo.dao.jpa;

import com.chenbo.dao.jpa.entity.JpaUser;
import com.chenbo.dao.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DaoJpaApplicationTests {

    @Autowired
    private UserRepository dao;

    @Test
    public void findTest() {
        List<JpaUser> jpaUsers = dao.findAll();
        jpaUsers.forEach(v -> {
            log.info("findAll:" + v);
        });

        jpaUsers = dao.findByName("胡龙飞");
        jpaUsers.forEach(v -> {
            log.info("findByName:" + v);
        });

        JpaUser jpaUser = dao.getOneByName("王天风");
        log.info("user:" + jpaUser);
    }

    @Test
    public void insertTest() {
        JpaUser jpaUser = new JpaUser();
        jpaUser.setId(1L);
        jpaUser.setName("");
        jpaUser.setAge(0);
        jpaUser.setEmail("");
        jpaUser.setManagerId(2L);
        jpaUser.setCreateTime(LocalDateTime.now());
        jpaUser.setUpdateTime(LocalDateTime.now());
        jpaUser.setVersion(0);
        jpaUser.setDeleted(0);

        jpaUser = dao.save(jpaUser);
        log.info("save" + jpaUser);

        jpaUser.setName("张三");
        log.info("save" + jpaUser);

        dao.delete(jpaUser);
        log.info("delete" + jpaUser);
    }

}
