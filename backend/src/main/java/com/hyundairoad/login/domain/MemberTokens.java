package com.hyundairoad.login.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * MemberTokens
 *
 * 작성자: 김진규
 */
@RequiredArgsConstructor
@Getter
public class MemberTokens {
    private final String refreshToken;
    private final String accessToken;
}
