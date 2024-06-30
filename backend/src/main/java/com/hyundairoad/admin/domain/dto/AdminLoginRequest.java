package com.hyundairoad.admin.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * AdminLoginRequest
 *
 * 작성자: 김진규
 */
public record AdminLoginRequest(
        @NotBlank(message = "ID를 입력해주세요.")
        @Min(3) @Max(30)
        String loginId,
        @NotBlank(message = "PW를 입력해주세요.")
        @Min(3) @Max(30)
        String password
) {
}
