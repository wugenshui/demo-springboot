package com.chenbo.demo.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author : chenbo
 * @date : 2020-04-11
 */
@Slf4j
@Configuration
public class ElasticsearchConfiguration {

    /**
     * 集群地址，多个用使用 {*，*，。。。}
     */
    @Value("${spring.elasticsearch.host}")
    private String host;


    @Value("${spring.elasticsearch.port}")
    private Integer port;

    /**
     * 使用的协议（http或者https）
     */
    @Value("${spring.elasticsearch.scheme}")
    private String scheme;

    @Value("${spring.elasticsearch.connection-timeout}")
    private Integer connectionTimeout;

    @Value("${spring.elasticsearch.socket-timout}")
    private Integer socketTimeout;

    @Value("${spring.elasticsearch.connection-request-timout}")
    private Integer connectionRequestTimout;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    /**
     * @return 封装 RestClient
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {

        if (Objects.isNull(host)) {
            log.error("必须指定ElasticSearch服务地址");
            return null;
        }

        String[] hosts = host.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            httpHosts[i] = new HttpHost(hosts[i], port, scheme);
        }


        return new RestHighLevelClient(RestClient.builder(httpHosts)
                .setRequestConfigCallback(requestConfigBuilder -> {
                            // 配置超时信息
                            requestConfigBuilder.setConnectTimeout(connectionTimeout);
                            requestConfigBuilder.setSocketTimeout(socketTimeout);
                            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimout);
                            return requestConfigBuilder;
                        }
                )
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    // 设置线程数
                    HttpAsyncClientBuilder httpAsyncClientBuilder = httpClientBuilder.setDefaultIOReactorConfig(
                            IOReactorConfig.custom()
                                    .setIoThreadCount(20)
                                    .build());
                    // 设置认证信息
                    httpAsyncClientBuilder.setDefaultCredentialsProvider(getCredentialsProvider());
                    return httpAsyncClientBuilder;
                })
        );
    }

    /**
     * 鉴权
     *
     * @return 鉴权配置
     */
    private CredentialsProvider getCredentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        System.out.println(username);
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        return credentialsProvider;
    }
}