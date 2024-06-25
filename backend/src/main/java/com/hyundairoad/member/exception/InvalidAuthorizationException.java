package com.hyundairoad.member.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InvalidAuthorizationException extends HyundaiRoadException {
    public InvalidAuthorizationException() {
        super(new ErrorCode(UNAUTHORIZED, "권한이 부여되지 않은 사용자입니다."));
    }
}
