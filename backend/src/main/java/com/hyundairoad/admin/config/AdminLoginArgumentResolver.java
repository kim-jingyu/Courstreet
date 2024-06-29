package com.hyundairoad.admin.config;

import com.hyundairoad.admin.exception.AdminNotFoundException;
import com.hyundairoad.admin.repository.AdminRepository;
import com.hyundairoad.auth.admin.AdminCheck;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.global.error.BadRequestException;
import com.hyundairoad.global.error.RefreshTokenException;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.repository.RefreshTokenRepository;
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

import static com.hyundairoad.global.constants.AuthCredentials.REFRESH_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * AdminLoginArgumentResolver
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Component
@RequiredArgsConstructor
public class AdminLoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final RefreshTokenRepository refreshTokenRepository;
    private final BearerAuthorizationExtractor bearerAuthorizationExtractor;
    private final JwtProvider jwtProvider;
    private final AdminRepository adminRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(Long.class)
                .hasParameterAnnotation(AdminCheck.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request == null) throw new BadRequestException();

        String refreshToken = extractRefreshToken(request.getCookies());
        String accessToken = bearerAuthorizationExtractor.extractAccessToken(webRequest.getHeader(AUTHORIZATION));
        jwtProvider.validateTokens(new MemberTokens(refreshToken, accessToken));

        Long adminId = Long.valueOf(jwtProvider.getSubject(accessToken));
        if (!adminRepository.existsById(adminId)) throw new AdminNotFoundException();
        return Accessor.admin(adminId);
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
        return REFRESH_TOKEN.equals(cookie.getName()) && refreshTokenRepository.existsById(cookie.getName());
    }
}
