package com.chenbo.baseutil.bean.dozer;

import com.chenbo.baseutil.bean.StudentDO;
import com.chenbo.baseutil.bean.StudentVO;
import com.chenbo.baseutil.util.bean.dozer.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2020-05-07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DozerTest {

    @Test
    public void copyBeanTest() {
        StudentDO studentDO = new StudentDO(1024L, "张三", 18, "11122223333");
        StudentVO studentVO = BeanMapper.map(studentDO, StudentVO.class);
        log.info("StudentVo: [{}]", studentVO);
        studentVO.setAge(16);
        log.info("studentDO: [{}]", studentDO);
    }

    @Test
    public void copyListTest() {
        List<StudentDO> studentDO = Arrays.asList(new StudentDO(1024L, "张三", 18, "11122223333"));
        List<StudentVO> studentVO = BeanMapper.mapList(studentDO, StudentVO.class);
        log.info("StudentVo: [{}]", studentVO);
        log.info("studentDO: [{}]", studentDO);
    }


}
