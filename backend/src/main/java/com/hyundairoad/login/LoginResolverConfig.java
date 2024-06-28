package com.hyundairoad.login;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * LoginResolverConfig
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Configuration
@RequiredArgsConstructor
public class LoginResolverConfig implements WebMvcConfigurer {

    private final LoginArgumentResolver loginArgumentResolver;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginArgumentResolver);
    }
}
