package com.chenbo.daomybatisplus.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局handler接收参数日期统一处理
 *
 * @author : chenbo
 * @date : 2019/12/5
 */
@Component
@Slf4j
public class DateConverterConfig implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        LocalDateTime date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            date = LocalDateTime.parse(source, formatter);
        } catch (Exception e) {
            log.error("全局：时间转换失败", e);
        }
        return date;
    }
}
