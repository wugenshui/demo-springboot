package com.github.wugenshui.generate.db.doc.mapper;

import com.github.wugenshui.generate.db.doc.entity.TableFileds;
import com.github.wugenshui.generate.db.doc.entity.Tables;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 查询表数据信息
 *
 * @author : chenbo
 * @date : 2020-04-05
 */
@Mapper
public interface MySqlTableMapper extends BaseTableMapper {

    /**
     * 获取指定数据库下所有表名和注释
     *
     * @param dbName 数据库名
     * @return 表集合
     */
    @Override
    @Select("select table_name as name,table_comment as comment from information_schema.tables where table_schema =#{dbName} order by table_name")
    List<Tables> getAllTables(@Param("dbName") String dbName);

    /**
     * 获取指定表信息
     *
     * @param tableName 表名
     * @return 字段集合
     */
    @Override
    @Select("SHOW FULL FIELDS FROM ${tableName}")
    List<TableFileds> getTable(@Param("tableName") String tableName);

}
