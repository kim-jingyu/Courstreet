package com.hyundairoad.auth.member;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * MemberOnly 어노테이션
 *
 * 작성자: 김진규
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface MemberOnly {
}
