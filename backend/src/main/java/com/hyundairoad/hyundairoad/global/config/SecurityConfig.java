package com.hyundairoad.hyundairoad.global.config;

import com.hyundairoad.hyundairoad.member.domain.vo.ROLE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/signup").permitAll()
                        .requestMatchers("/admin").hasRole(ROLE.ADMIN.name())
                        .requestMatchers("/mypage/**").hasAnyRole(ROLE.ADMIN.name(), ROLE.ADMIN.name())
                        .anyRequest().authenticated())
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProcess")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .permitAll())
                .oauth2Login((auth) -> auth.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .permitAll())
                .logout((auth) -> auth
                        .logoutUrl("/logout"))
                .rememberMe((rembmerMe) -> rembmerMe
                        .key("security")
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(60*60*24*30))
                .csrf(AbstractHttpConfigurer::disable).build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
