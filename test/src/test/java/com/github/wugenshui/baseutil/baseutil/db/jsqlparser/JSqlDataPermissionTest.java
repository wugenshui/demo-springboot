package com.github.wugenshui.baseutil.baseutil.db.jsqlparser;

import cn.hutool.core.collection.CollUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据权限模拟
 *
 * @author : chenbo
 * @date : 2023-11-26
 */
public class JSqlDataPermissionTest {

    @ParameterizedTest
    @CsvSource({
            "SELECT * FROM user u,SELECT * FROM user u WHERE user.ou_id = 1",
            "SELECT * FROM user,SELECT * FROM user WHERE user.ou_id = 1",
            "SELECT * FROM a,SELECT * FROM a",
    })
    void addTest(String sourceSql, String excepted) {
        Assertions.assertEquals(excepted, addDataPermission(sourceSql));
    }

    /**
     * 添加机构查询权限
     *
     * @param sql 源sql
     * @return 添加数据权限后的sql
     */
    public String addDataPermission(String sql) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            // 查询表名，是否存在需要进行数据权限控制的表
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(select);
            Map<String, String> dataPermissionColumn = getDataPermissionColumn(tableList);
            if (CollUtil.isNotEmpty(dataPermissionColumn)) {
                if (selectBody instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectBody;
                    dataPermissionColumn.forEach((tableName, columnName) -> {
                        EqualsTo equalsTo = new EqualsTo();
                        equalsTo.setLeftExpression(new Column(new Table(tableName), columnName));
                        equalsTo.setRightExpression(new LongValue(1));
                        plainSelect.setWhere(equalsTo);
                    });
                    sql = plainSelect.toString();
                }
            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return sql;
    }

    Map<String, String> needDataPermissionColumns = new HashMap<String, String>() {
        {
            put("ou", "id");
            put("user", "ou_id");
            put("role", "ou_id");
        }
    };

    public Map<String, String> getDataPermissionColumn(List<String> tableList) {
        Map<String, String> result = new HashMap<>();
        for (String table : tableList) {
            if (needDataPermissionColumns.containsKey(table)) {
                result.put(table, needDataPermissionColumns.get(table));
            }
        }
        return result;
    }
}
