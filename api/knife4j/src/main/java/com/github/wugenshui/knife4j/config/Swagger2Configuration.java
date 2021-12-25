package com.github.wugenshui.knife4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author : chenbo
 * @date : 2020-02-09
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Configuration {

    /**
     * api接口包扫描路径
     */
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.github.wugenshui.knife4j.controller";

    /**
     * 版本号
     */
    public static final String VERSION = "1.0.0";

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("api-swagger")
                        .description("swagger RESTful APIs")
                        .termsOfServiceUrl("https://www.github.wugenshui/")
                        .contact(new Contact("chenbo", "", ""))
                        .version(VERSION)
                        .build())
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