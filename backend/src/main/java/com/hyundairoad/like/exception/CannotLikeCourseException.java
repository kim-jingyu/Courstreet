package com.hyundairoad.like.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * CannotLikeCourseException
 *
 * 작성자: 김진규
 */
public class CannotLikeCourseException extends HyundaiRoadException {
    public CannotLikeCourseException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "코스 좋아요에 실패했습니다!"));
    }
}
