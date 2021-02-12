package com.chenbo.baseutil.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @author : chenbo
 * @date : 2020-11-05
 */
@Slf4j
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // json中多余的参数不报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Date 格式化格式
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // LocalDateTime 序列化反序列化
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * 将 java对象 序列化 为 字符串
     *
     * @param data 对象
     * @return
     */
    public static String serialize(Object data) {
        try {
            String result = MAPPER.writeValueAsString(data);
            return result;
        } catch (JsonProcessingException e) {
            log.error("serialize error", e);
        }
        return null;
    }

    /**
     * 将 字符串 反序列化 为 java对象
     *
     * @param jsonData json字符串
     * @param beanType java对象类型
     * @param <T>      数据类型
     * @return
     */
    public static <T> T deserialize(String jsonData, Class<T> beanType) {
        try {
            T result = MAPPER.readValue(jsonData, beanType);
            return result;
        } catch (Exception e) {
            log.error("deserialize error", e);
        }

        return null;
    }

    /**
     * 将 字符串 反序列化 为 java集合对象
     *
     * @param jsonData json字符串
     * @param beanType java对象类型
     * @param <T>      数据类型
     * @return
     */
    public static <T> List<T> deserializeList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);

        try {
            List<T> resultList = MAPPER.readValue(jsonData, javaType);
            return resultList;
        } catch (Exception e) {
            log.error("deserializeList error", e);
        }

        return null;
    }

}
