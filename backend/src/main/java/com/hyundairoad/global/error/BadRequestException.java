package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * BadRequestException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
public class BadRequestException extends HyundaiRoadException {
    public BadRequestException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."));
    }
}
