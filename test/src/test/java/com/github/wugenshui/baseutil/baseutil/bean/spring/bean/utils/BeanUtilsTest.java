package com.github.wugenshui.baseutil.baseutil.bean.spring.bean.utils;

import com.github.wugenshui.baseutil.baseutil.bean.Goods;
import com.github.wugenshui.baseutil.baseutil.bean.ShoppingCar;
import com.github.wugenshui.baseutil.baseutil.util.bean.BeanUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @author : chenbo
 * @date : 2020-06-09
 */
@SpringBootTest
public class BeanUtilsTest {
    @Test
    public void copyPropertiesTest() {
        ShoppingCar sourceObject = ShoppingCar.builder().buyer("张三").createTime(new Date()).build();
        ShoppingCar targetObject = ShoppingCar.builder().buyer("李四").updateTime(LocalDateTime.now()).goodCount(1).build();

        // 执行初始化
        System.out.println("将source属性名与destination属性名相同的值进行拷贝覆盖，即使为null");
        System.out.println("-----------------------------------------------");

        System.out.println("拷贝前 Source = " + sourceObject);
        System.out.println("拷贝前 Target = " + targetObject);
        BeanUtil.copyProperties(sourceObject, targetObject);
        System.out.println("拷贝后 Result = " + targetObject);
        System.out.println("-----------------------------------------------");

        sourceObject.setGoodCount(123);
        sourceObject.setUpdateTime(LocalDateTime.now());
        sourceObject.setGoods(Arrays.asList(Goods.builder().name("香水").price(1200L).build()));
        targetObject.setCreateTime(new Date(2132132124132L));
        System.out.println("拷贝前 Source = " + sourceObject);
        System.out.println("拷贝前 Target = " + targetObject);
        BeanUtil.copyProperties(sourceObject, targetObject);
        System.out.println("拷贝后 Result = " + targetObject);
        System.out.println("-----------------------------------------------");
    }


    @Test
    public void copyPropertiesIgnoreNullTest() {
        ShoppingCar sourceObject = ShoppingCar.builder().buyer("张三").createTime(new Date()).build();
        ShoppingCar targetObject = ShoppingCar.builder().buyer("李四").updateTime(LocalDateTime.now()).goodCount(1).build();

        // 执行初始化
        System.out.println("将source属性名与destination属性名相同的值进行拷贝覆盖，如果null");
        System.out.println("-----------------------------------------------");

        System.out.println("拷贝前 Source = " + sourceObject);
        System.out.println("拷贝前 Target = " + targetObject);
        BeanUtil.copyPropertiesIgnoreNull(sourceObject, targetObject);
        System.out.println("拷贝后 Result = " + targetObject);
        System.out.println("-----------------------------------------------");

        sourceObject.setGoodCount(123);
        sourceObject.setUpdateTime(LocalDateTime.now());
        sourceObject.setGoods(Arrays.asList(Goods.builder().name("香水").price(1200L).build()));
        targetObject.setCreateTime(new Date(2132132124132L));
        System.out.println("拷贝前 Source = " + sourceObject);
        System.out.println("拷贝前 Target = " + targetObject);
        BeanUtil.copyPropertiesIgnoreNull(sourceObject, targetObject);
        System.out.println("拷贝后 Result = " + targetObject);
        System.out.println("-----------------------------------------------");
    }
}
