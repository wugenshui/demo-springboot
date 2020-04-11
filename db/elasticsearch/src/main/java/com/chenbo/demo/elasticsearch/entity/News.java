package com.chenbo.demo.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chenbo
 * @date : 2020-04-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private String title;
    private String tag;
    private String publishTime;
}