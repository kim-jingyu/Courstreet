package com.hyundairoad.place.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

public class WithWhomNotFoundException extends HyundaiRoadException {
    public WithWhomNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "누구랑 갈지 찾지못했습니다."));
    }
}
