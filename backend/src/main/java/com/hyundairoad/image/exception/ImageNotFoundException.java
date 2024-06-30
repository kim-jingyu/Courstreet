package com.hyundairoad.image.exception;

import com.hyundairoad.global.error.ErrorCode;
import com.hyundairoad.global.error.HyundaiRoadException;
import org.springframework.http.HttpStatus;

/**
 * ImageNotFoundException
 *
 * 작성자: 김진규
 */
public class ImageNotFoundException extends HyundaiRoadException {
    public ImageNotFoundException() {
        super(new ErrorCode(HttpStatus.BAD_REQUEST, "이미지를 찾을 수 없습니다."));
    }
}
