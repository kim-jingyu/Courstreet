package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidJwtException extends HyundaiRoadException {

    public InvalidJwtException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."));
    }
}
