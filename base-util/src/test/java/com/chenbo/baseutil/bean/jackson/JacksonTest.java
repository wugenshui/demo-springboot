package com.chenbo.baseutil.bean.jackson;

import com.chenbo.baseutil.bean.StudentVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@SpringBootTest
public class JacksonTest {
    @Test
    public void mapTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //json中多余的参数不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Date 格式化格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // LocalDateTime 序列化反序列化
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //JavaTimeModule javaTimeModule = new JavaTimeModule();
        //javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        //javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        //javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //objectMapper.registerModule(javaTimeModule);

        String userStr = objectMapper.writeValueAsString(getStudentDO("张三"));
        System.out.println("userStr = " + userStr);

        StudentVO user = objectMapper.readValue(userStr, StudentVO.class);
        System.out.println("user = " + user);
    }

    private StudentVO getStudentDO(String name) {
        return StudentVO.builder()
                .id(1024L)
                .name(name)
                .age(18)
                .mobile("11122223333")
                .createTime(new Date())
                .updateTime(LocalDateTime.now())
                .updateDate(LocalDate.now())
                .tempName(name)
                .build();
    }
}
