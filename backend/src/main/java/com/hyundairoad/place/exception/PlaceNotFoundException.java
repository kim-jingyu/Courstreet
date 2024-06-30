package com.hyundairoad.place.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * PlaceNotFoundException
 *
 * 작성자: 김진규, 조희정
 */
public class PlaceNotFoundException extends HyundaiRoadException {
    public PlaceNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "해당 장소를 찾지못했습니다."));
    }
}
