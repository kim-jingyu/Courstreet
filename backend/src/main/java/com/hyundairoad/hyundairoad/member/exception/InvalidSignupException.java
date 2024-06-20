package com.hyundairoad.hyundairoad.member.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;

public class InvalidSignupException extends HyundaiRoadException {
    public InvalidSignupException(ErrorMsg errorMsg) {
        super(errorMsg);
    }
}
