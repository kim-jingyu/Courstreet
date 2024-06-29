package com.hyundairoad.mail.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class MailTryCountException extends HyundaiRoadException {
    public MailTryCountException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이메일 인증 요청 횟수 5번이 초과되었습니다."));
    }
}
