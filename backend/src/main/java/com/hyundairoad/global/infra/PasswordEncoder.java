package com.hyundairoad.global.infra;

/**
 * PasswordEncoder
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface PasswordEncoder {
    String encode(String password);
    Boolean match(String password, String newPassword);
}
