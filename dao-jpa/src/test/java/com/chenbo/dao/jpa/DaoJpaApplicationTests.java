package com.chenbo.dao.jpa;

import com.chenbo.dao.jpa.entity.User;
import com.chenbo.dao.jpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class DaoJpaApplicationTests {

    @Autowired
    private UserRepository dao;

    @Test
    void findTest() {
        List<User> users = dao.findAll();
        users.forEach(v -> {
            log.info(v.toString());
        });

        users = dao.findByName("胡龙飞");
        users.forEach(v -> {
            log.info("findByName:" + v.toString());
        });
    }


    @Test
    void insertTest() {
        User user = new User();
        user.setId(1L);
        user.setName("");
        user.setAge(0);
        user.setEmail("");
        user.setManagerId(2L);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setVersion(0);
        user.setDeleted(0);

        user = dao.save(user);
        log.info("save" + user.toString());

        user.setName("张三");
        log.info("save" + user.toString());

        dao.delete(user);
        log.info("delete" + user);
    }

}
