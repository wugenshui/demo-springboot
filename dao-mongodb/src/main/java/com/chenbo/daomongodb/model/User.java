package com.chenbo.daomongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

import static org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING;

/**
 * 用户实体
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Data
@Document("user")
// 创建联合索引
@CompoundIndexes({
        // 联合索引 name 索引名称 、def 索引字段、createDate降序、name升序
        @CompoundIndex(name = "user_index", def = "{'registerTime': -1, 'name': 1}")
})
public class User implements Serializable {

    /**
     * 标记id字段
     */
    @Id
    private String id;

    private String username;

    private String loginname;

    /**
     * 此字段不映射到数据库
     */
    @Transient
    private String password;


    /**
     * 修改映射到数据库中的名称
     */
    @Field("register_time")
    private Date registerTime;

    private String phone;

    private String sex;

    private int age;

    /**
     * 创建单字段索引（默认ASCENDING 升序、DESCENDING 降序）
     */
    @Indexed(direction = DESCENDING)
    private Long experience;

    private String[] favourite;

    @DBRef
    private UserPost userPost;
}