package com.hyundairoad.admin.domain.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * AdminCreateRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record AdminCreateRequest(
        @NotBlank(message = "ID를 입력해주세요.")
        @Min(3) @Max(30)
        String loginId,
        @NotBlank(message = "PW를 입력해주세요.")
        @Min(8) @Max(30)
        String password
) {
}
