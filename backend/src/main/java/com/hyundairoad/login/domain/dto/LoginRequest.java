package com.hyundairoad.login.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * LoginRequest
 *
 * 작성자: 김진규
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class LoginRequest {
    @NotBlank(message = "인증 코드를 입력해주세요.")
    private String code;
}
