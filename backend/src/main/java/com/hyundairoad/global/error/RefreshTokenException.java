package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RefreshTokenException extends HyundaiRoadException {

    public RefreshTokenException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다."));
    }
}
