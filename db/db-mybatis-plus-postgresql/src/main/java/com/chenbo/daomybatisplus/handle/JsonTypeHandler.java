package com.chenbo.daomybatisplus.handle;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Object.class)
public class JsonTypeHandler extends BaseTypeHandler<Object> {

    private static final PGobject jsonObject = new PGobject();
    private Class<Object> type;

    public JsonTypeHandler(Class<Object> type) {
        Assert.notNull(type, "Type argument cannot be null", new Object[0]);
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        jsonObject.setType("json");
        jsonObject.setValue(JacksonSerializer.toJSONString(parameter));
        ps.setObject(i, jsonObject);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return StringUtils.isBlank(json) ? null : this.parse(json);
    }

    private Object parse(String json) {
        return JacksonSerializer.parseObject(json, this.type);
    }

}