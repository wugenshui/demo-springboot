package com.chenbo.daomybatisplus.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenbo.daomybatisplus.auth.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author chenbo
 * @since 2019-12-01
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 恢复删除标记
     *
     * @param id 主键
     * @return 影响行数
     */
    @Update("UPDATE User SET deleted = 0 WHERE ID = #{id}")
    int resumeDelete(@Param("id") Long id);


    /**
     * 版本恢复
     *
     * @param id 主键
     * @return 影响行数
     */
    @Update("UPDATE User SET version = 1 WHERE ID = #{id}")
    int resumeVersion(@Param("id") Long id);
}
