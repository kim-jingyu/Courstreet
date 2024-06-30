package com.hyundairoad.login.infra;

import com.hyundairoad.global.error.InvalidJwtException;
import org.springframework.stereotype.Component;

/**
 * BearerAuthorizationExtractor
 *
 * 작성자: 김진규
 */
@Component
public class BearerAuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public String extractAccessToken(String header) {
        if (header != null && header.startsWith(BEARER_TYPE)) {
            return header.substring(BEARER_TYPE.length()).trim();
        }
        throw new InvalidJwtException();
    }
}
