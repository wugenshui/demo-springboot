package com.chenbo.demo.elasticsearch.config;

import com.chenbo.demo.elasticsearch.bean.ElasticsearchConfigBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Elasticsearch配置类
 *
 * @author : chenbo
 * @date : 2020-04-11
 */
@Slf4j
@Configuration
public class ElasticsearchConfiguration {

    private static Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Autowired
    private ElasticsearchConfigBean config;

    /**
     * @return 封装 RestClient
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {

        if (Objects.isNull(config.getHost())) {
            log.error("必须指定ElasticSearch服务地址");
            return null;
        }

        String[] hosts = config.getHost().split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            httpHosts[i] = new HttpHost(hosts[i], config.getPort(), config.getScheme());
        }

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts)
                .setRequestConfigCallback(requestConfigBuilder -> {
                            // 配置httpClient连接超时信息
                            requestConfigBuilder.setConnectTimeout(config.getConnectionTimeout());
                            requestConfigBuilder.setSocketTimeout(config.getSocketTimeout());
                            requestConfigBuilder.setConnectionRequestTimeout(config.getConnectionTimeout());
                            return requestConfigBuilder;
                        }
                )
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    // 设置线程数
                    HttpAsyncClientBuilder httpAsyncClientBuilder = httpClientBuilder
                            .setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(20).build());
                    httpClientBuilder.setMaxConnTotal(config.getMaxConnectNum());
                    httpClientBuilder.setMaxConnPerRoute(config.getMaxConnectPerRoute());

                    // 设置认证信息
                    if (!StringUtils.isEmpty(config.getUsername()) && !StringUtils.isEmpty(config.getPassword())) {
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(config.getUsername(), config.getPassword()));
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                    return httpAsyncClientBuilder;
                })
        );

        return client;
    }
}