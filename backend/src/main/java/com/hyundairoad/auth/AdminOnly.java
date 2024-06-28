package com.hyundairoad.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * AdminOnly 어노테이션
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface AdminOnly {
}
