package com.mybatis.plus.mysql.sims.service;

import com.mybatis.plus.mysql.sims.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author chenbo
 * @since 2021-12-17
 */
public interface IClassService extends IService<Class> {
    boolean removeByClassId(String classId);
}
