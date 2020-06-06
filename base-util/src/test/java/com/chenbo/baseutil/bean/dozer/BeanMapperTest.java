package com.chenbo.baseutil.bean.dozer;

import com.chenbo.baseutil.bean.StudentDO;
import com.chenbo.baseutil.bean.StudentVO;
import com.chenbo.baseutil.util.bean.dozer.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@SpringBootTest
@Slf4j
public class BeanMapperTest {

    @Test
    public void mapTest() {
        StudentDO studentDO = getStudentDO("张三");

        StudentVO studentVO1 = BeanMapper.map(studentDO, StudentVO.class);
        log.info("StudentVo: [{}]", studentVO1);
        studentVO1.setAge(16);
        log.info("studentDO: [{}]", studentDO);

        StudentVO studentVO2 = new StudentVO();
        BeanMapper.map(studentDO, studentVO2);
        log.info("studentVO: [{}]", studentDO);
    }

    @Test
    public void mapListTest() {
        List<StudentDO> studentDO = Arrays.asList(getStudentDO("张三"), getStudentDO("李四"));
        List<StudentVO> studentVO = BeanMapper.mapList(studentDO, StudentVO.class);
        log.info("StudentVo: [{}]", studentVO);
        log.info("studentDO: [{}]", studentDO);
    }


    private StudentDO getStudentDO(String name) {
        return StudentDO.builder()
                .id(1024L)
                .name(name)
                .age(18)
                .mobile("11122223333")
                .createTime(new Date())
                .updateTime(LocalDateTime.now())
                .build();
    }

}
