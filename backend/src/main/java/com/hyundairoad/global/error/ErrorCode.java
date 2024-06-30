package com.hyundairoad.global.error;

import org.springframework.http.HttpStatus;

/**
 * ErrorCode
 *
 * 작성자: 김진규
 */
public record ErrorCode (HttpStatus status, String message) {
}
