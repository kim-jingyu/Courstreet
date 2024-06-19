package com.hyundairoad.hyundairoad.global.advice;

import com.hyundairoad.hyundairoad.global.exception.HyundaiRoadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler(HyundaiRoadException.class)
    public ResponseEntity<Object> handleHyundaiException(HyundaiRoadException ex, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }
}
