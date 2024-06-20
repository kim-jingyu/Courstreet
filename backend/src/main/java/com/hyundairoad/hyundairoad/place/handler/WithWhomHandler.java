package com.hyundairoad.hyundairoad.place.handler;

import com.hyundairoad.hyundairoad.place.domain.WithWhom;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithWhomHandler extends BaseTypeHandler<WithWhom> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, WithWhom parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getWithWhom());
    }

    @Override
    public WithWhom getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getWithWhomByThemeName(rs.getString(columnName));
    }

    @Override
    public WithWhom getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getWithWhomByThemeName(rs.getString(columnIndex));
    }

    @Override
    public WithWhom getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getWithWhomByThemeName(cs.getString(columnIndex));
    }

    private WithWhom getWithWhomByThemeName(String value) {
        for (WithWhom withWhom : WithWhom.values()) {
            if (withWhom.getWithWhom().equals(value)) {
                return withWhom;
            }
        }
        return null;
    }
}
