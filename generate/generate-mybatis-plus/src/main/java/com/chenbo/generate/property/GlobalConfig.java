package com.chenbo.generate.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : chenbo
 * @date : 2021-01-21
 */
@Data
@Component
@ConfigurationProperties(prefix = "generate.global-config")
public class GlobalConfig {
    /**
     * 生成文件的输出目录
     */
    private String outputDir = "D://";

    /**
     * 是否打开输出目录
     */
    private Boolean open = true;

    /**
     * 开发人员
     */
    private String author = null;
}
