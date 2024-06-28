package com.hyundairoad.course.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * CoursePlaceNotFoundException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public class CoursePlaceNotFoundException extends HyundaiRoadException {
    public CoursePlaceNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "코스별 장소 찾기에 실패했습니다!"));
    }
}
