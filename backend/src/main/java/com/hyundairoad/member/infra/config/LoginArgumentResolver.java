package com.hyundairoad.member.infra.config;

import com.hyundairoad.member.domain.auth.Auth;
import com.hyundairoad.member.infra.providers.JwtProvider;
import com.hyundairoad.member.infra.supporters.BearerTokenExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String REFRESH_TOKEN = "refresh-token";
    private final JwtProvider jwtProvider;
    private final BearerTokenExtractor extractor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter
                .withContainingClass(Long.class)
                .hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return null;
    }
}
