package com.hyundairoad.global.infra;

/**
 * PasswordEncoder
 *
 * 작성자: 김진규
 */
public interface PasswordEncoder {
    String encode(String password);
    Boolean match(String password, String newPassword);
}
