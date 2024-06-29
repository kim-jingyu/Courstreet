package com.hyundairoad.auth.member;

import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.global.error.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * MemberOnlyChecker
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Aspect
@Component
public class MemberOnlyChecker {

    @Before("@annotation(com.hyundairoad.auth.member.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Accessor.class::isInstance)
                .map(Accessor.class::cast)
                .filter(Accessor::isMember)
                .findFirst()
                .orElseThrow(AuthException::new);
    }
}
