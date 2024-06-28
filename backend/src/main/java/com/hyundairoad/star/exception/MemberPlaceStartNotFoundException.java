package com.hyundairoad.star.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * MemberPlaceStartNotFoundException
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public class MemberPlaceStartNotFoundException extends HyundaiRoadException {
    public MemberPlaceStartNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "해당 장소에 대한 별점 정보를 찾을 수 없습니다."));
    }
}
