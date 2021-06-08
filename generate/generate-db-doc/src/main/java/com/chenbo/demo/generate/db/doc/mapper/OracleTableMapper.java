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
 * @date : 2020-10-19
 */
@Mapper
public interface OracleTableMapper extends BaseTableMapper {

    /**
     * 获取指定数据库下所有表名和注释
     *
     * @param dbName 数据库名
     * @return 表集合
     */
    @Override
    @Select("SELECT t.TABLE_NAME NAME,c.COMMENTS \"COMMENT\" FROM USER_TABLES t\n" +
            "LEFT JOIN USER_TAB_COMMENTS c\n" +
            "ON c.TABLE_NAME=t.TABLE_NAME")
    List<Tables> getAllTables(@Param("dbName") String dbName);

    /**
     * 获取指定表信息
     *
     * @param tableName 表名
     * @return 字段集合
     */
    @Override
    @Select("SELECT \n" +
            "\tt.COLUMN_NAME FIELD,t.DATA_TYPE TYPE,t.NULLABLE \"NULL\",c.COMMENTS \"COMMENT\",t.DATA_DEFAULT \"DEFAULT\",DATA_PRECISION \"PRECISION\",DATA_SCALE \"SCALE\",DATA_LENGTH \"LENGTH\",\n" +
            "\t(SELECT con.CONSTRAINT_TYPE FROM SYS.USER_CONS_COLUMNS cc\n" +
            "\tLEFT JOIN SYS.USER_CONSTRAINTS con ON con.CONSTRAINT_NAME=cc.CONSTRAINT_NAME AND con.CONSTRAINT_TYPE='P'\n" +
            "\tWHERE cc.COLUMN_NAME= t.COLUMN_NAME AND cc.TABLE_NAME= t.TABLE_NAME AND rownum=1\n" +
            "\t) AS \"KEY\"\n" +
            "FROM SYS.ALL_TAB_COLS t\n" +
            "LEFT JOIN SYS.USER_COL_COMMENTS c ON c.COLUMN_NAME=t.COLUMN_NAME\n" +
            "WHERE t.TABLE_NAME='${tableName}' AND c.TABLE_NAME='${tableName}' \n" +
            "ORDER BY COLUMN_ID")
    List<TableFileds> getTable(@Param("tableName") String tableName);
}
