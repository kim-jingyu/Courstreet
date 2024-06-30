package com.hyundairoad.place.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * ThemeNotFoundException
 *
 * 작성자: 김진규, 조희정
 */
public class ThemeNotFoundException extends HyundaiRoadException {
    public ThemeNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "테마를 찾지 못했습니다."));
    }
}
