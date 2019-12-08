package com.chenbo.daomongodb.service;

import com.chenbo.daomongodb.model.User;
import com.chenbo.daomongodb.model.UserPost;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
@Service
public class UserTemplateService {
    @Autowired
    MongoTemplate mongoTemplate;

    public User insert() {
        User user = new User();

        UserPost userPost = new UserPost();
        userPost.setName("作业人员");
        userPost.setLevel(5);
        userPost = mongoTemplate.insert(userPost);

        user.setUsername("张三");
        user.setLoginname("张三丰");
        user.setPassword("zsf");
        user.setRegisterTime(new Date());
        user.setPhone("12345");
        user.setSex("男");
        user.setAge(18);
        user.setFavourite(new String[]{"吃饭", "唱歌"});
        user.setUserPost(userPost);
        user.setExperience(12L);

        return mongoTemplate.insert(user);
    }

    public DeleteResult delete(User user) {
        UserPost userPost = user.getUserPost();
        mongoTemplate.remove(userPost);
        return mongoTemplate.remove(user);
    }

    public List<User> findAll() {
        List<User> user = mongoTemplate.findAll(User.class);
        return user;
    }

    /**
     * 将查询条件符合的全部文档更新
     *
     * @param user
     * @return
     */
    public UpdateResult update(User user) {
        Query query = new Query();
        Update update = Update.update("age", "28");
        return mongoTemplate.updateMulti(query, update, User.class);
    }

    public User findById(String id) {
        User user2 = mongoTemplate.findOne(Query.query(Criteria.where("")), User.class);

        User user = mongoTemplate.findById(id, User.class);
        return user;
    }


    public HashMap find() {
        HashMap hashMap = new HashMap(4);

        // 聚合统计
        TypedAggregation<UserPost> aggregation = Aggregation.newAggregation(UserPost.class,
                Aggregation.group("name").count().as("count")
                        .first("name").as("postName"),
                Aggregation.project("count", "postName"),
                Aggregation.sort(Sort.Direction.DESC, "postName")
        );
        AggregationResults<UserPost> resultAggregation = mongoTemplate.aggregate(aggregation, UserPost.class);
        hashMap.put("resultAggregation", resultAggregation.getMappedResults());

        // 查询小于当前时间的数据，并按时间倒序排列
        Sort sort = Sort.by(Sort.Direction.DESC, "register_time");
        List<User> findTestList = mongoTemplate.find(Query.query(Criteria.where("register_time").lt(new Date())).with(sort), User.class);
        hashMap.put("findTestList", findTestList);

        // 使用findOne返回排序在最上面的一条
        User findOneTest = mongoTemplate.findOne(Query.query(Criteria.where("register_time").lt(new Date())).with(sort), User.class);
        hashMap.put("findOneTest", findOneTest);

        // 模糊查询
        List<User> findTestList1 = mongoTemplate.find(Query.query(Criteria.where("register_time").lt(new Date()).and("username").regex("张")), User.class);
        hashMap.put("findTestList1", findTestList1);

        // 分页查询（每页2行第1页）【0页开始】
        Pageable pageable = PageRequest.of(1, 2, sort);
        List<User> findTestList2 = mongoTemplate.find(Query.query(Criteria.where("register_time").lt(new Date())).with(pageable), User.class);
        // 共多少条
        Long count = mongoTemplate.count(Query.query(Criteria.where("register_time").lt(new Date())), User.class);
        // 返回分页对象
        Page<User> page = new PageImpl<>(findTestList2, pageable, count);
        hashMap.put("page", page);

        // 分页查询（通过起始行和数量也可以自己实现分页逻辑）
        List<User> findTestList3 = mongoTemplate.find(Query.query(Criteria.where("register_time").lt(new Date())).with(sort).skip(3).limit(3), User.class);
        hashMap.put("findTestList3", findTestList3);

        // 分页查询（通过起始行和数量也可以自己实现分页逻辑）
        GroupBy groupBy = GroupBy.key("name")
                .initialDocument("{ count: 0 }")
                .reduceFunction("function(doc, prev) { prev.count += 1 }");
        GroupByResults<UserPost> findGroup = mongoTemplate.group("user_post", groupBy, UserPost.class);
        hashMap.put("group", findGroup);

        return hashMap;
    }
}
