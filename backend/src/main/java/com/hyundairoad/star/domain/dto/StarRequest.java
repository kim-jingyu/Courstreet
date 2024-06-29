package com.hyundairoad.star.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

/**
 * StarRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record StarRequest(
        @NotBlank(message = "별점을 입력해주세요")
        @Max(value = 5, message = "0~5점 사이만 부여할 수 있습니다.")
        Double rate) {
}
