package com.chenbo.demo.elasticsearch.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Elasticsearch配置参数
 *
 * @author : chenbo
 * @date : 2020-04-28
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchConfigBean {
    /**
     * 集群地址，多个用使用 {*，*，。。。}
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 使用的协议（http或者https）
     */
    private String scheme;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 最大连接数
     */
    private Integer maxConnectNum = 50;

    /**
     * 最大路由连接数
     */
    private Integer maxConnectPerRoute = 10;

    /**
     * 连接请求超时时间
     */
    private Integer connectionRequestTimout = 1000;

    /**
     * 连接超时时间
     */
    private Integer connectionTimeout = 3000;

    /**
     * socket超时时间
     */
    private Integer socketTimeout = 15000;

    /**
     * 索引--分片数
     */
    private Integer numberOfShards = 2;

    /**
     * 索引--副本数
     */
    private Integer numberOfReplicas = 0;

}
