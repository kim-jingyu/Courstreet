package com.hyundairoad.member.infra.providers;

import com.hyundairoad.member.infra.config.credentials.JwtCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    private final JwtCredentials jwtCredentials;


}
