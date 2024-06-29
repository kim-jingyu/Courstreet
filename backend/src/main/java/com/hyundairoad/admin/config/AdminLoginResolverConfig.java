package com.hyundairoad.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * AdminLoginResolverConfig
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Configuration
@RequiredArgsConstructor
public class AdminLoginResolverConfig implements WebMvcConfigurer {
    private AdminLoginArgumentResolver adminLoginArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(adminLoginArgumentResolver);
    }
}
