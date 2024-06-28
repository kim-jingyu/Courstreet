package com.hyundairoad.like.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * CannotCancelLikePlaceException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public class CannotCancelLikePlaceException extends HyundaiRoadException {
    public CannotCancelLikePlaceException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "장소 좋아요 취소에 실패했습니다!"));
    }
}
