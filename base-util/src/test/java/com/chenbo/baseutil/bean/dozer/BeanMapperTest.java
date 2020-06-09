package com.chenbo.baseutil.bean.dozer;

import com.chenbo.baseutil.bean.Goods;
import com.chenbo.baseutil.bean.ShoppingCar;
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
    public void baseTest() {
        ShoppingCar sourceObject = ShoppingCar.builder().buyer("张三").createTime(new Date()).tempName("新的名称").build();
        ShoppingCar destinationObject = ShoppingCar.builder().buyer("李四").goodCount(1).build();

        // 执行初始化
        destinationObject = BeanMapper.map(destinationObject, ShoppingCar.class);
        System.out.println("将source属性名与destination属性名相同的值进行拷贝覆盖，即使为null");
        System.out.println("-----------------------------------------------");

        System.out.println("拷贝前 S = " + sourceObject);
        System.out.println("拷贝前 T = " + destinationObject);
        BeanMapper.map(sourceObject, destinationObject);
        System.out.println("拷贝后 R = " + destinationObject);
        System.out.println("-----------------------------------------------");

        sourceObject.setGoodCount(123);
        // docker 默认不支持LocalDateTime的映射
        //sourceObject.setUpdateTime(LocalDateTime.now());
        sourceObject.setGoods(Arrays.asList(Goods.builder().name("香水").price(1200L).build()));
        destinationObject.setCreateTime(new Date(2132132124132L));
        destinationObject.setTempName("临时名称");
        System.out.println("拷贝前 S = " + sourceObject);
        System.out.println("拷贝前 T = " + destinationObject);
        BeanMapper.map(sourceObject, destinationObject);
        System.out.println("拷贝后 R = " + destinationObject);
        System.out.println("-----------------------------------------------");
    }

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
