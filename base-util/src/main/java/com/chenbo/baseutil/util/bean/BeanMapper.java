package com.chenbo.baseutil.util.bean;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Bean转换工具类
 *
 * @author : chenbo
 * @date : 2020-05-07
 */
public class BeanMapper {
    /**
     * 基于Dozer映射对象的属性
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null || destinationClass == null) {
            return null;
        }
        T destinationObject = null;
        try {
            destinationObject = destinationClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化异常");
        }
        BeanUtils.copyProperties(source, destinationObject);
        return destinationObject;
    }

    /**
     * 基于Dozer将源对象的值映射到目标对象中
     */
    public static void map(Object source, Object destinationObject) {
        if (source == null || destinationObject == null) {
            return;
        }
        BeanUtils.copyProperties(source, destinationObject);
    }

    /**
     * 基于Dozer映射Collection中对象的属性
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        if (sourceList != null) {
            for (Object sourceObject : sourceList) {
                T destinationObject = null;
                try {
                    destinationObject = destinationClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("实例化异常");
                }
                BeanUtils.copyProperties(sourceObject, destinationObject);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }
}
