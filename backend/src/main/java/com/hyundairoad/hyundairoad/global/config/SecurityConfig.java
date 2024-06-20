package com.hyundairoad.hyundairoad.global.config;

import com.hyundairoad.hyundairoad.global.handler.CustomOAuth2AuthenticationSuccessHandler;
import com.hyundairoad.hyundairoad.member.domain.vo.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.yml")
public class SecurityConfig {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/signup").permitAll()
                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())
                        .requestMatchers("/mypage/**").hasAnyRole(Role.ADMIN.name(), Role.ADMIN.name())
                        .anyRequest().authenticated())
                .oauth2Login((auth) -> auth.loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .rememberMe((rembmerMe) -> rembmerMe
                        .key("security")
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(60*60*24*30))
                .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new CustomOAuth2AuthenticationSuccessHandler();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration googleRegistration = ClientRegistration.withRegistrationId("google")
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/google")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .scope("openid", "profile", "email")
                .build();

        ClientRegistration kakaoRegistration = ClientRegistration.withRegistrationId("kakao")
                .clientId(kakaoClientId)
                .clientSecret(kakaoClientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/kakao")
                .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                .tokenUri("https://kauth.kakao.com/oauth/token")
                .scope("profile", "account_email")
                .build();

        return new InMemoryClientRegistrationRepository(googleRegistration, kakaoRegistration);
    }
}
