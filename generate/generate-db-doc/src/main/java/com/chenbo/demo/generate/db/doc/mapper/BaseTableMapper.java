package com.chenbo.demo.generate.db.doc.mapper;

import com.chenbo.demo.generate.db.doc.entity.TableFileds;
import com.chenbo.demo.generate.db.doc.entity.Tables;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : chenbo
 * @date : 2021-01-25
 */
public interface BaseTableMapper {

    /**
     * 获取指定数据库下所有表名和注释
     *
     * @param dbName 数据库名
     * @return 表集合
     */
    List<Tables> getAllTables(@Param("dbName") String dbName);

    /**
     * 获取指定表信息
     *
     * @param tableName 表名
     * @return 字段集合
     */
    List<TableFileds> getTable(@Param("tableName") String tableName);

}