package com.github.wugenshui.knife4j.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : chenbo
 * @date : 2020-02-09
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Configuration {

    /**
     * api接口包扫描路径
     */
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.github.wugenshui.knife4j.controller";
    //
    public static final String VERSION = "1.0.0";

    //
    //@Bean
    //public Docket createRestApi() {
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .apiInfo(apiInfo())
    //            .select()
    //            .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
    //            // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
    //            .paths(PathSelectors.any())
    //            .build();
    //}
    //
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api-swagger")
                .version(VERSION)
                .build();
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}