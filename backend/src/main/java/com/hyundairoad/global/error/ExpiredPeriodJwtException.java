package com.hyundairoad.global.error;

import lombok.Getter;

/**
 * ExpiredPeriodJwtException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
public class ExpiredPeriodJwtException extends AuthException {

    public ExpiredPeriodJwtException() {
        super();
    }
}
