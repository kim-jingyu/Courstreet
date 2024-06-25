package com.hyundairoad.mail.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class InvalidSimpleMailException extends HyundaiRoadException {
    public InvalidSimpleMailException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "간편 이메일 인증에 실패했습니다."));
    }
}
