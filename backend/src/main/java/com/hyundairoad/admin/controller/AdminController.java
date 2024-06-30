package com.hyundairoad.admin.controller;

import com.hyundairoad.admin.domain.dto.AdminCreateRequest;
import com.hyundairoad.admin.domain.dto.AdminLoginRequest;
import com.hyundairoad.admin.domain.dto.AdminPasswordUpdateRequest;
import com.hyundairoad.admin.service.AdminService;
import com.hyundairoad.auth.admin.AdminCheck;
import com.hyundairoad.auth.admin.AdminOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.domain.dto.AccessTokenResponse;
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
 * AdminController
 *
 * 작성자: 김진규
 */
@Tag(name = "관리자", description = "관리자 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    /**
     * 관리자를 생성합니다.
     *
     * @param accessor 인증된 관리자 정보
     * @param request  관리자 생성 요청 정보
     * @return 생성된 관리자 ID
     */
    @Operation(summary = "관리자를 생성합니다.", description = "관리자를 생성하는 API입니다.")
    @AdminOnly
    @PostMapping
    public ResponseEntity<Long> createAdmin(@AdminCheck Accessor accessor, @RequestBody @Validated AdminCreateRequest request) {
        return ResponseEntity.status(CREATED).body(adminService.createAdmin(request));
    }

    /**
     * 관리자 비밀번호를 수정합니다.
     *
     * @param accessor 인증된 관리자 정보
     * @param id       관리자 ID
     * @param request  비밀번호 수정 요청 정보
     * @return 수정된 관리자 ID
     */
    @Operation(summary = "관리자 비밀번호를 수정합니다.", description = "관리자 비밀번호를 수정하는 API입니다.")
    @AdminOnly
    @PatchMapping("/{id}/password")
    public ResponseEntity<Long> updatePassword(@AdminCheck Accessor accessor, @PathVariable Long id, @RequestBody @Validated AdminPasswordUpdateRequest request) {
        return ResponseEntity.ok().body(adminService.updatePassword(id, request));
    }

    /**
     * 관리자로 로그인합니다.
     *
     * @param request  로그인 요청 정보
     * @param response HTTP 응답 객체
     * @return 액세스 토큰 응답
     */
    @Operation(summary = "관리자로 로그인합니다.", description = "관리자로 로그인하는 API입니다.")
    @PostMapping("/admin/login")
    public ResponseEntity<AccessTokenResponse> loginAdmin(@RequestBody @Validated AdminLoginRequest request, HttpServletResponse response) {
        MemberTokens memberTokens = adminService.login(request);
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
    public ResponseEntity<AccessTokenResponse> extendLoginAdmin(@CookieValue("refreshToken") String refreshToken, @RequestHeader("Authorization") String authorizationHeader) {
        String renewalRefreshToken = adminService.renewalAccessToken(refreshToken, authorizationHeader);
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(renewalRefreshToken));
    }

    /**
     * 로그아웃 요청을 처리합니다.
     *
     * @param accessor     인증된 관리자 정보
     * @param refreshToken 리프레시 토큰 (쿠키에서 가져옴)
     * @return 처리 결과
     */
    @Operation(summary = "로그아웃 요청을 처리합니다.", description = "회원의 로그아웃을 처리하는 API입니다.")
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AdminCheck Accessor accessor, @CookieValue("refreshToken") String refreshToken) {
        adminService.removeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }
}
