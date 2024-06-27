package com.hyundairoad.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends HyundaiRoadException {
    public BadRequestException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."));
    }
}
