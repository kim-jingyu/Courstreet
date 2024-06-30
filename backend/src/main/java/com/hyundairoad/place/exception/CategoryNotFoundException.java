package com.hyundairoad.place.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * CategoryNotFoundException
 *
 * 작성자: 김진규, 조희정
 */
public class CategoryNotFoundException extends HyundaiRoadException {
    public CategoryNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "카테고리를 찾지 못했습니다."));
    }
}
