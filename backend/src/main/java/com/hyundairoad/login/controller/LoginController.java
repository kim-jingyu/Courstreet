package com.hyundairoad.login.controller;

import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.auth.member.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.domain.dto.AccessTokenResponse;
import com.hyundairoad.login.domain.dto.LoginRequest;
import com.hyundairoad.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.hyundairoad.global.constants.AuthCredentials.COOKIE_AGE_SECONDS;
import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * 로그인 컨트롤러
 *
 * 작성자: 김진규
 */
@Tag(name = "로그인", description = "로그인 API입니다.")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    /**
     * 소셜 로그인 요청을 처리합니다.
     *
     * @param provider     소셜 로그인 제공자 (예: google, facebook)
     * @param loginRequest 로그인 요청 정보 (code 포함)
     * @param response     HTTP 응답 객체
     * @return 액세스 토큰 응답
     */
    @Operation(summary = "소셜 로그인 요청을 처리합니다.", description = "소셜 로그인 제공자를 통해 로그인하는 API입니다.")
    @PostMapping("/login/{provider}")
    public ResponseEntity<AccessTokenResponse> login(@PathVariable String provider, @RequestBody @Validated LoginRequest loginRequest, HttpServletResponse response) {
        MemberTokens memberTokens = loginService.login(provider, loginRequest.getCode());
        ResponseCookie cookie = ResponseCookie.from("refreshToken", memberTokens.getRefreshToken())
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader(SET_COOKIE, cookie.toString());
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(memberTokens.getAccessToken()));
    }

    /**
     * 액세스 토큰을 갱신합니다.
     *
     * @param refreshToken        리프레시 토큰 (쿠키에서 가져옴)
     * @param authorizationHeader 기존 액세스 토큰 (헤더에서 가져옴)
     * @return 갱신된 액세스 토큰 응답
     */
    @Operation(summary = "액세스 토큰을 갱신합니다.", description = "리프레시 토큰을 사용하여 액세스 토큰을 갱신하는 API입니다.")
    @PostMapping("/token")
    public ResponseEntity<AccessTokenResponse> extendLogin(@CookieValue("refreshToken") String refreshToken, @RequestHeader("Authorization") String authorizationHeader) {
        String renewalRefreshToken = loginService.renewalAccessToken(refreshToken, authorizationHeader);
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(renewalRefreshToken));
    }

    /**
     * 로그아웃 요청을 처리합니다.
     *
     * @param accessor     회원 정보
     * @param refreshToken 리프레시 토큰 (쿠키에서 가져옴)
     * @return 처리 결과
     */
    @Operation(summary = "로그아웃 요청을 처리합니다.", description = "회원의 로그아웃을 처리하는 API입니다.")
    @MemberOnly
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@MemberCheck Accessor accessor, @CookieValue("refreshToken") String refreshToken) {
        loginService.removeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
