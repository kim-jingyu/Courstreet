package com.hyundairoad.hyundairoad.member.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;

public class InvalidMimeMailException extends HyundaiRoadException {
    public InvalidMimeMailException(ErrorMsg errorMsg) {
        super(errorMsg);
    }
}
