package com.chenbo.daomybatisplus.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充类
 *
 * @author : chenbo
 * @date : 2019/12/1
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间，这里设置的是类字段名称，不是数据库字段名称
     */
    private static final String CREATE_TIME = "createTime";

    /**
     * 更新时间
     */
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("start insert fill ....");
        if (metaObject.hasSetter(CREATE_TIME)) {
            setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        }
        if (metaObject.hasSetter(UPDATE_TIME)) {
            setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("start update fill ....");
        Object fieldValByName = getFieldValByName(UPDATE_TIME, metaObject);
        if (fieldValByName == null) {
            setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }
}
