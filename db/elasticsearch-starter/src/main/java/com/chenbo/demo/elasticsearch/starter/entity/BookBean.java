package com.chenbo.demo.elasticsearch.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;

/**
 * @author : chenbo
 * @date : 2020-05-25
 */
@Document(indexName = "book", type = "_doc", shards = 5, replicas = 2)
@TypeAlias("allbook")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    @InnerField(suffix = "text", type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    @InnerField(suffix = "keyword", type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss || date || epoch_millis")
    private String updateTime;

    //@Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    //private LocalDateTime creatTime;
}