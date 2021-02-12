package com.chenbo.baseutil.util.bean.dozer;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Bean转换工具类(基于Dozer)
 *
 * @author : chenbo
 * @date : 2020-05-07
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static final DozerBeanMapper DOZER_BEAN_MAPPER = new DozerBeanMapper();

    /**
     * 映射对象的属性
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null || destinationClass == null) {
            return null;
        }
        return DOZER_BEAN_MAPPER.map(source, destinationClass);
    }

    /**
     * 将源对象的值映射到目标对象中
     */
    public static void map(Object source, Object destinationObject) {
        if (source == null || destinationObject == null) {
            return;
        }
        DOZER_BEAN_MAPPER.map(source, destinationObject);
    }

    /**
     * 映射Collection中对象的属性
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        if (sourceList != null) {
            for (Object sourceObject : sourceList) {
                T destinationObject = DOZER_BEAN_MAPPER.map(sourceObject, destinationClass);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }
}
