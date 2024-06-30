package com.hyundairoad.auth.member;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Member 어노테이션
 *
 * 작성자: 김진규
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface MemberCheck {
}
