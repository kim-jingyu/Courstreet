package com.hyundairoad.admin.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * AdminAlreadyExistsException
 *
 * 작성자: 김진규
 */
public class AdminAlreadyExistsException extends HyundaiRoadException {
    public AdminAlreadyExistsException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미 존재하는 관리자입니다."));
    }
}
