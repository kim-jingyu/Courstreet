package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends HyundaiRoadException {
    public AuthException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."));
    }
}
