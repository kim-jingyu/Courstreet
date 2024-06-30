package com.hyundairoad.login;

import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.global.error.BadRequestException;
import com.hyundairoad.global.error.RefreshTokenException;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.infra.BearerAuthorizationExtractor;
import com.hyundairoad.login.infra.JwtProvider;
import com.hyundairoad.login.repository.RefreshTokenRepository;
import com.hyundairoad.member.exception.MemberNotFoundException;
import com.hyundairoad.member.repository.MemberRepository;
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

import static com.hyundairoad.global.constants.AuthCredentials.REFRESH_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * LoginArgumentResolver
 *
 * 작성자: 김진규
 */
@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final BearerAuthorizationExtractor extractor;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(Long.class)
                .hasParameterAnnotation(MemberCheck.class);
    }

    @Override
    public Accessor resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) {
            throw new BadRequestException();
        }

        try {
            String refreshToken = extractRefreshToken(request.getCookies());
            String accessToken = extractor.extractAccessToken(webRequest.getHeader(AUTHORIZATION));
            jwtProvider.validateTokens(new MemberTokens(refreshToken, accessToken));

            Long memberId = Long.valueOf(jwtProvider.getSubject(accessToken));
            if (!memberRepository.existsById(memberId)){
                throw new MemberNotFoundException();
            }
            return Accessor.member(memberId);
        } catch (RefreshTokenException e) {
            return Accessor.guest();
        }
    }

    private String extractRefreshToken(Cookie... cookies) {
        if (cookies == null) throw new RefreshTokenException();
        return Arrays.stream(cookies)
                .filter(this::isValidRefreshToken)
                .findFirst()
                .orElseThrow(RefreshTokenException::new)
                .getValue();
    }

    private boolean isValidRefreshToken(Cookie cookie) {
        return REFRESH_TOKEN.equals(cookie.getName()) && refreshTokenRepository.existsById(cookie.getValue());
    }
}
