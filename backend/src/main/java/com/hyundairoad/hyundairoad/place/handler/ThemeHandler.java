package com.hyundairoad.hyundairoad.place.handler;

import com.hyundairoad.hyundairoad.place.domain.Theme;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeHandler extends BaseTypeHandler<Theme> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Theme parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getThemeName());
    }

    @Override
    public Theme getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getThemeByThemeName(rs.getString(columnName));
    }

    @Override
    public Theme getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getThemeByThemeName(rs.getString(columnIndex));
    }

    @Override
    public Theme getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getThemeByThemeName(cs.getString(columnIndex));
    }

    private Theme getThemeByThemeName(String value) {
        for (Theme theme : Theme.values()) {
            if (theme.getThemeName().equals(value)) {
                return theme;
            }
        }
        return null;
    }
}
