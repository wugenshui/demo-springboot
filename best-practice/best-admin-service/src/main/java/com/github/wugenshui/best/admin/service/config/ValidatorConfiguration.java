package com.github.wugenshui.best.admin.service.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Validator配置类
 *
 * @author : chenbo
 * @date : 2020-04-27
 */
@Configuration
public class ValidatorConfiguration {

    /**
     * Validator配置
     * 快速返回模式，当有一个参数校验异常，直接抛出异常
     *
     * @return
     */
    @Bean
    public Validator validator() {
        // 设置快速失败返回，当有一个校验错误时就返回异常
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                //.addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
