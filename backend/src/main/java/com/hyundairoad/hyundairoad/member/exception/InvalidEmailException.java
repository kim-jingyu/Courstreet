package com.hyundairoad.hyundairoad.member.exception;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;

public class InvalidEmailException extends HyundaiRoadException {
    public InvalidEmailException(ErrorMsg errorMsg) {
        super(errorMsg);
    }
}
