package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * AuthException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
public class AuthException extends HyundaiRoadException {
    public AuthException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."));
    }
}
