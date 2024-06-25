package com.hyundairoad.member.domain.auth.inspector;

import com.hyundairoad.member.domain.auth.LoginMember;
import com.hyundairoad.member.exception.InvalidAuthorizationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AdminInspector {
    @Pointcut("@annotation(com.hyundairoad.member.domain.auth.Admin)")
    public void adminPointCut() {}

    @Before("adminPointCut()")
    public void inspect(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(LoginMember.class::isInstance)
                .map(LoginMember.class::cast)
                .filter(LoginMember::isAdmin)
                .findFirst()
                .orElseThrow(InvalidAuthorizationException::new);
    }
}
