package com.hyundairoad.hyundairoad.member.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;

public class InvalidLoginException extends HyundaiRoadException {
    public InvalidLoginException(ErrorMsg errorMsg) {
        super(errorMsg);
    }
}
