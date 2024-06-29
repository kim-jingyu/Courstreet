package com.hyundairoad.auth.admin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * AdminCheck 어노테이션
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface AdminCheck {
}
