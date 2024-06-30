package com.hyundairoad.admin.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * AdminPasswordUpdateRequest
 *
 * 작성자: 김진규
 */
public record AdminPasswordUpdateRequest(
        @NotBlank(message = "PW를 입력해주세요.")
        @Min(3)
        @Min(8)
        String password
) {
}
