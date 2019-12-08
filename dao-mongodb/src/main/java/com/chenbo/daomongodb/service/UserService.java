package com.chenbo.daomongodb.service;

import com.chenbo.daomongodb.dao.UserDao;
import com.chenbo.daomongodb.model.User;
import com.chenbo.daomongodb.model.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User insert() {
        User user = new User();

        UserPost userPost = new UserPost();
        userPost.setName("作业人员");
        userPost.setLevel(5);

        user.setUsername("张三");
        user.setLoginname("张三丰");
        user.setPassword("zsf");
        user.setRegisterTime(new Date());
        user.setPhone("12345");
        user.setSex("男");
        user.setAge(18);
        user.setFavourite(new String[]{"吃饭", "唱歌"});
        user.setPost(userPost);

        return userDao.insert(user);
    }

    public void delete(String id) {
        userDao.deleteById(id);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public List<User> findAll() {
        List<User> user = userDao.findAll();
        return user;
    }

    public User update(User user) {
        return userDao.save(user);
    }

    public Optional<User> findById(String id) {
        Optional<User> user = userDao.findById(id);
        return user;
    }
}
