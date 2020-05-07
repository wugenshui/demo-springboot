package com.chenbo.baseutil.bean.dozer;

import com.chenbo.baseutil.bean.StudentDO;
import com.chenbo.baseutil.bean.StudentVO;
import com.chenbo.baseutil.util.bean.dozer.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
        StudentDO studentDO = new StudentDO(1024L, "张三", 18, "11122223333");
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
        List<StudentDO> studentDO = Arrays.asList(new StudentDO(1024L, "张三", 18, "11122223333"));
        List<StudentVO> studentVO = BeanMapper.mapList(studentDO, StudentVO.class);
        log.info("StudentVo: [{}]", studentVO);
        log.info("studentDO: [{}]", studentDO);
    }
}
