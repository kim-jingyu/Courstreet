package com.hyundairoad.global.config;

import com.hyundairoad.global.infra.BCryptPasswordEncoder;
import com.hyundairoad.global.infra.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SecurityConfig
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
