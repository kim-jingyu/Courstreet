package com.hyundairoad.global.error;

import lombok.Getter;

/**
 * HyundaiRoadException
 *
 * 작성자: 김진규
 */
@Getter
public class HyundaiRoadException extends RuntimeException {
    private final ErrorCode errorCode;

    public HyundaiRoadException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }
}
