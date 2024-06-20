package com.hyundairoad.hyundairoad.course.handler;

import com.hyundairoad.hyundairoad.course.domain.vo.Visibility;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisibilityHandler extends BaseTypeHandler<Visibility> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Visibility parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Visibility getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getValueByThemeName(rs.getString(columnName));
    }

    @Override
    public Visibility getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getValueByThemeName(rs.getString(columnIndex));
    }

    @Override
    public Visibility getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getValueByThemeName(cs.getString(columnIndex));
    }

    private Visibility getValueByThemeName(String value) {
        for (Visibility theme : Visibility.values()) {
            if (theme.getValue().equals(value)) {
                return theme;
            }
        }
        return null;
    }
}
