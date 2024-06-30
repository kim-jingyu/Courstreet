package com.hyundairoad.global.error;

import lombok.Builder;

/**
 * ErrorResponse
 *
 * 작성자: 김진규
 */
@Builder
public record ErrorResponse(int status, String message) {
    @Override
    public String toString() {
        return "status= " + status +
                ", message= " + message;
    }
}
