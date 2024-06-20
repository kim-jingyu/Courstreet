package com.hyundairoad.hyundairoad.member.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;

public class InvalidSimpleMailException extends HyundaiRoadException {
    public InvalidSimpleMailException(ErrorMsg errorMsg) {
        super(errorMsg);
    }
}
