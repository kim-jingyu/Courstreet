package com.hyundairoad.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * LoginRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class LoginRequest {
    private String code;
}
