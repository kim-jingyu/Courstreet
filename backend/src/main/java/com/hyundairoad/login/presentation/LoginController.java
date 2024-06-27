package com.hyundairoad.login.presentation;

import com.hyundairoad.auth.Auth;
import com.hyundairoad.auth.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.dto.AccessTokenResponse;
import com.hyundairoad.login.dto.LoginRequest;
import com.hyundairoad.login.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Tag(name = "로그인", description = "로그인 API입니당.")
public class LoginController {

    public static final int COOKIE_AGE_SECONDS = 604800;

    private final LoginService loginService;

    @PostMapping("/login/{provider}")
    public ResponseEntity<AccessTokenResponse> login(
            @PathVariable final String provider,
            @RequestBody final LoginRequest loginRequest,
            final HttpServletResponse response
    ) {
        final MemberTokens memberTokens = loginService.login(provider, loginRequest.getCode());
        final ResponseCookie cookie = ResponseCookie.from("refresh-token", memberTokens.getRefreshToken())
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader(SET_COOKIE, cookie.toString());
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(memberTokens.getAccessToken()));
    }

    @PostMapping("/token")
    public ResponseEntity<AccessTokenResponse> extendLogin(
            @CookieValue("refresh-token") final String refreshToken,
            @RequestHeader("Authorization") final String authorizationHeader
    ) {
        final String renewalRefreshToken = loginService.renewalAccessToken(refreshToken, authorizationHeader);
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(renewalRefreshToken));
    }

    @DeleteMapping("/logout")
    @MemberOnly
    public ResponseEntity<Void> logout(
            @Auth final Accessor accessor,
            @CookieValue("refresh-token") final String refreshToken) {
        loginService.removeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
