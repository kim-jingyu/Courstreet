package com.hyundairoad.member.domain.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public record MemberTokens(String accessToken, String refreshToken) {
}
