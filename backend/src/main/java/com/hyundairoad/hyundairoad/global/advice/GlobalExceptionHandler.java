package com.hyundairoad.hyundairoad.global.advice;

import com.hyundairoad.hyundairoad.constants.ErrorMsg;
import com.hyundairoad.hyundairoad.global.domain.dto.ErrorResult;
import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_500 = "내부 서버 오류";
    public static final String ERROR_HYUNDAI = "현대로 서비스 오류";

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResult> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ErrorResult.builder()
                        .code(ERROR_500)
                        .message(ErrorMsg.INTERNAL_SERVER_ERROR.getMessage())
                        .build());
    }

    @ExceptionHandler(HyundaiRoadException.class)
    public ResponseEntity<ErrorResult> handleHyundaiException(HyundaiRoadException ex, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResult.builder()
                        .code(ERROR_HYUNDAI)
                        .message(ex.getMessage())
                        .build());
    }
}
