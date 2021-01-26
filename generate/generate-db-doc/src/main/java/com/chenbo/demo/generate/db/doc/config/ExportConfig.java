package com.chenbo.demo.generate.db.doc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : chenbo
 * @date : 2021-01-25
 */
@Data
@Component
@ConfigurationProperties(prefix = "export")
public class ExportConfig {
    /**
     * 配置忽略的项目，用,分割
     */
    private String ignoreTables;

    /**
     * 数据库文档模板
     */
    private String dbTemplate;

    /**
     * 数据表模板
     */
    private String tableTemplate;
}
