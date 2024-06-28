package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * RefreshTokenException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
public class RefreshTokenException extends HyundaiRoadException {

    public RefreshTokenException() {
        super(new ErrorCode(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다."));
    }
}
