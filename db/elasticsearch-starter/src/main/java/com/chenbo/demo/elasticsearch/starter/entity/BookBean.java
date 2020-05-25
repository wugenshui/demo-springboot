package com.chenbo.demo.elasticsearch.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author : chenbo
 * @date : 2020-05-25
 */
@Document(indexName = "book", type = "_doc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {
    @Id
    private String id;
    private String title;
    private String author;
    private String postDate;
}