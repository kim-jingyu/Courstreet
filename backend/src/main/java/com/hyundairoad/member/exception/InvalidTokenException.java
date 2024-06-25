package com.hyundairoad.member.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InvalidTokenException extends HyundaiRoadException {
    public InvalidTokenException() {
        super(new ErrorCode(UNAUTHORIZED, "유효하지 않은 토큰입니다."));
    }
}
