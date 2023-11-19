package com.chenbo.daomybatisplus.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import java.sql.*;

@Slf4j
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

    private static final String TYPE_NAME_VARCHAR = "varchar";
    private static final String TYPE_NAME_INTEGER = "integer";
    private static final String TYPE_NAME_BOOLEAN = "boolean";
    private static final String TYPE_NAME_NUMERIC = "numeric";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType)
            throws SQLException {
        String typename = null;
        if (parameter instanceof Integer[]) {
            typename = TYPE_NAME_INTEGER;
        } else if (parameter instanceof String[]) {
            typename = TYPE_NAME_VARCHAR;
        } else if (parameter instanceof Boolean[]) {
            typename = TYPE_NAME_BOOLEAN;
        } else if (parameter instanceof Double[]) {
            typename = TYPE_NAME_NUMERIC;
        }

        if (typename == null) {
            throw new TypeException("arraytypehandler parameter typename error, your type is " + parameter.getClass().getName());
        }

        // 这2行是关键的代码，创建array，然后ps.setarray(i, array)就可以了
        Array array = ps.getConnection().createArrayOf(typename, parameter);
//        OracleConnection oraConn = ps.getConnection().unwrap(OracleConnection.class);
//        Array array = oraConn.createOracleArray("MY_ARRAY_TYPE", parameter);
        ps.setArray(i, array);
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getArray(rs.getArray(columnName));
    }


    @Override
    public Object[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getArray(rs.getArray(columnIndex));
    }


    @Override
    public Object[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getArray(cs.getArray(columnIndex));
    }

    private Object[] getArray(Array array) {

        if (array == null) {
            return null;
        }

        try {
            return (Object[]) array.getArray();
        } catch (SQLException e) {
            log.error("ArrayTypeHandler getArray SQLException", e);
        }
        return null;

    }
}