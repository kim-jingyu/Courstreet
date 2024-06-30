package com.hyundairoad.global.infra;


import org.mindrot.jbcrypt.BCrypt;

/**
 * BCryptPasswordEncoder
 *
 * 작성자: 김진규
 */
public class BCryptPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Boolean match(String password, String newPassword) {
        return BCrypt.checkpw(password, newPassword);
    }
}
