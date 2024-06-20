package com.hyundairoad.hyundairoad.member.handler;

import com.hyundairoad.hyundairoad.member.domain.vo.Sex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexHandler extends BaseTypeHandler<Sex> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Sex getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getSexByValue(value);
    }

    @Override
    public Sex getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getSexByValue(value);
    }

    @Override
    public Sex getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getSexByValue(value);
    }

    private Sex getSexByValue(String value) {
        for (Sex sex : Sex.values()) {
            if (sex.getValue().equals(value)) {
                return sex;
            }
        }
        return null;
    }
}
