package com.hyundairoad.admin.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * AdminNotFoundException
 *
 * 작성자: 김진규
 */
public class AdminNotFoundException extends HyundaiRoadException {
    public AdminNotFoundException() {
        super(new ErrorCode(HttpStatus.NOT_FOUND, "관리자 정보를 찾을 수 없습니다."));
    }
}
