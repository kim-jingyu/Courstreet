package com.hyundairoad.auth.admin;

import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.global.error.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AdminOnlyChecker
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Aspect
@Component
public class AdminOnlyChecker {

    @Before("@annotation(com.hyundairoad.auth.admin.AdminOnly)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Accessor.class::isInstance)
                .map(Accessor.class::cast)
                .filter(Accessor::isAdmin)
                .findFirst()
                .orElseThrow(AuthException::new);
    }
}
