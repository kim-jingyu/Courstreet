package com.hyundairoad.global.error;

import lombok.Getter;

@Getter
public class ExpiredPeriodJwtException extends AuthException {

    public ExpiredPeriodJwtException() {
        super();
    }
}
