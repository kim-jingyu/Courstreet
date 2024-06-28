package com.hyundairoad.member.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * GenderNotFoundException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public class GenderNotFoundException extends HyundaiRoadException {
    public GenderNotFoundException() {
        super(new ErrorCode(NOT_FOUND, "성별을 확인해주세요."));
    }
}
