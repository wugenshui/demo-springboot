package com.github.wugenshui.baseutil.baseutil.bean.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.wugenshui.baseutil.baseutil.bean.StudentVO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author : chenbo
 * @date : 2020-06-06
 */
@SpringBootTest
public class JacksonTest {
    @Test
    public void aTest() throws JsonProcessingException {
        List<StudentVO> list = new ArrayList<>();
        list.add(getStudentDO("张三"));
        List<StudentVO> newList = read(list, StudentVO.class);
        System.out.println(newList);
    }

    public <T> List<T> read(List<T> list, Class<T> clz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // json中多余的参数不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Date 格式化格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // LocalDateTime 序列化反序列化
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, clz);
        return mapper.readValue(mapper.writeValueAsString(list), type);
    }

    @Test
    public void apiTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // LocalDateTime 序列化反序列化
        objectMapper.registerModule(new JavaTimeModule());
        //json中多余的参数不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StudentVO vo = objectMapper.readValue("{\"name1\":\"123\"}", StudentVO.class);
        System.out.println("vo = " + vo);



        // Date 格式化格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //JavaTimeModule javaTimeModule = new JavaTimeModule();
        //javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        //javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        //javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //objectMapper.registerModule(javaTimeModule);

        StudentVO user = getStudentDO("张三");
        String userStr = objectMapper.writeValueAsString(user);
        System.out.println("userW = " + user);

        user = objectMapper.readValue(userStr, StudentVO.class);
        System.out.println("userR = " + user);
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
