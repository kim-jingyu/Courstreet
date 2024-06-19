package com.hyundairoad.hyundairoad.global.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HyundaiRoadException extends RuntimeException {
    public HyundaiRoadException() {
        super();
    }

    public HyundaiRoadException(ErrorMsg errorMsg) {
        super(errorMsg.getMessage());
        log.error("에러가 발생했습니다. {}", errorMsg.name());
    }
}
