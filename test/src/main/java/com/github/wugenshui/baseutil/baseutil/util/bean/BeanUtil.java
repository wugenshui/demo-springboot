package com.github.wugenshui.baseutil.baseutil.util.bean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Bean转换工具类
 *
 * @author : chenbo
 * @date : 2020-05-07
 */
public class BeanUtil {

    /**
     * 将源对象的值映射到目标对象中
     *
     * @param source      数据源对象
     * @param targetClass 目标对象class
     * @param <T>
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化异常");
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 将源对象的值映射到目标对象中，即使源对象属性为null也会映射
     *
     * @param source 数据源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 将源对象的值映射到目标对象中，忽略源对象中值为null的属性
     *
     * @param source 数据源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 映射Collection中对象的属性
     *
     * @param sourceList  数据源集合
     * @param targetClass 目标对象class
     * @param <T>
     * @return 目标对象集合
     */
    public static <T> List<T> copyList(Collection sourceList, Class<T> targetClass) {
        List<T> destinationList = new ArrayList<>();
        if (sourceList != null) {
            for (Object sourceObject : sourceList) {
                T destinationObject = null;
                try {
                    destinationObject = targetClass.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("实例化异常");
                }
                BeanUtils.copyProperties(sourceObject, destinationObject);
                destinationList.add(destinationObject);
            }
        }
        return destinationList;
    }

    /**
     * 获取对象中为null的属性名称
     *
     * @param source 目标对象
     * @return 属性名称数组
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
