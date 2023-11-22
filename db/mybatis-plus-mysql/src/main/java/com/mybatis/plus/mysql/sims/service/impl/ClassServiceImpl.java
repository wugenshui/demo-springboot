package com.mybatis.plus.mysql.sims.service.impl;

import com.mybatis.plus.mysql.sims.entity.Class;
import com.mybatis.plus.mysql.sims.mapper.ClassMapper;
import com.mybatis.plus.mysql.sims.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author chenbo
 * @since 2023-11-22
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {
    @Override
    public boolean removeByClassId(String classId) {
        return lambdaUpdate().eq(Class::getClassId, classId).remove();
    }
}
