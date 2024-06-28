package com.hyundairoad.login;

import com.hyundairoad.auth.Auth;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.global.error.BadRequestException;
import com.hyundairoad.global.error.RefreshTokenException;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.domain.repository.RefreshTokenRepository;
import com.hyundairoad.login.infra.BearerAuthorizationExtractor;
import com.hyundairoad.login.infra.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * LoginArgumentResolver
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@RequiredArgsConstructor
@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String REFRESH_TOKEN = "refresh-token";

    private final JwtProvider jwtProvider;

    private final BearerAuthorizationExtractor extractor;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.withContainingClass(Long.class)
                .hasParameterAnnotation(Auth.class);
    }

    @Override
    public Accessor resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            throw new BadRequestException();
        }

        try {
            final String refreshToken = extractRefreshToken(request.getCookies());
            final String accessToken = extractor.extractAccessToken(webRequest.getHeader(AUTHORIZATION));
            jwtProvider.validateTokens(new MemberTokens(refreshToken, accessToken));

            final Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));
            return Accessor.member(memberId);
        } catch (final RefreshTokenException e) {
            return Accessor.guest();
        }
    }

    private String extractRefreshToken(final Cookie... cookies) {
        if (cookies == null) {
            throw new RefreshTokenException();
        }
        return Arrays.stream(cookies)
                .filter(this::isValidRefreshToken)
                .findFirst()
                .orElseThrow(RefreshTokenException::new)
                .getValue();
    }

    private boolean isValidRefreshToken(final Cookie cookie) {
        return REFRESH_TOKEN.equals(cookie.getName()) &&
                refreshTokenRepository.existsById(cookie.getValue());
    }
}
