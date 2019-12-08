package com.chenbo.daomongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 用户岗位
 *
 * @author : chenbo
 * @date : 2019-12-08
 */
@Document("user_post")
@Data
public class UserPost implements Serializable {
    @Id
    private String id;
    /**
     * 岗位名称
     */
    @Indexed
    private String name;

    /**
     * 岗位等级
     */
    private Integer level;
}
