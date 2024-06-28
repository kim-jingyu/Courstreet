package com.hyundairoad.global.error;

import org.springframework.http.HttpStatus;

/**
 * ErrorCode
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record ErrorCode (HttpStatus status, String message) {
}
