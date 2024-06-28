package com.hyundairoad.global.error;

import lombok.Getter;

/**
 * HyundaiRoadException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
public class HyundaiRoadException extends RuntimeException {
    private final ErrorCode errorCode;

    public HyundaiRoadException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
