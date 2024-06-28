package com.hyundairoad.global.error;

import lombok.Builder;

/**
 * ErrorResponse
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Builder
public record ErrorResponse(int status, String message) {
    @Override
    public String toString() {
        return "status= " + status +
                ", message= " + message;
    }
}
