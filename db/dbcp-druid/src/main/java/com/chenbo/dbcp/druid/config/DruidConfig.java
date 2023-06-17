package com.chenbo.dbcp.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-09-27
 */
@Configuration
public class DruidConfig {
    // 该注解向bean自动注入对应的属性，属性在配置文件配置
    
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置druid的监控
    // 1.配置管理后台的servlet

    @Bean
    public ServletRegistrationBean statViewServlet() {
        // druid监控页面的url
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>(4);
        // 用户名
        initParams.put("loginUsername", "admin");
        // 密码
        initParams.put("loginPassword", "123456");
        // 允许IP
        initParams.put("allow", "");
        // 拒绝IP
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    // 2.配置一个web监控的filter,监控sql

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,*.html,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
