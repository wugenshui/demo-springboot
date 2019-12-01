package com.chenbo.daomybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 *
 * @author : chenbo
 * @date : 2019/12/1
 */
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 乐观锁插件（乐观锁：适合多读场景、悲观锁：适合多写场景）
     *
     * @return 乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
