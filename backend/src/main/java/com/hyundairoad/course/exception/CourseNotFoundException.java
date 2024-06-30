package com.hyundairoad.course.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * CourseNotFoundException
 *
 * 작성자: 김진규, 남진수
 */
public class CourseNotFoundException extends HyundaiRoadException {
    public CourseNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "코스 정보를 찾지 못했습니다."));
    }
}
