package com.hyundairoad.admin.service;

import com.hyundairoad.admin.domain.Admin;
import com.hyundairoad.admin.domain.dto.AdminCreateRequest;
import com.hyundairoad.admin.domain.dto.AdminLoginRequest;
import com.hyundairoad.admin.domain.dto.AdminPasswordUpdateRequest;
import com.hyundairoad.admin.exception.AdminAlreadyExistsException;
import com.hyundairoad.admin.exception.AdminNotFoundException;
import com.hyundairoad.global.error.RefreshTokenException;
import com.hyundairoad.global.infra.PasswordEncoder;
import com.hyundairoad.admin.repository.AdminRepository;
import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.login.domain.MemberTokens;
import com.hyundairoad.login.domain.RefreshToken;
import com.hyundairoad.login.repository.RefreshTokenRepository;
import com.hyundairoad.login.infra.BearerAuthorizationExtractor;
import com.hyundairoad.login.infra.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AdminService
 *
 * 작성자: 김진규
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BearerAuthorizationExtractor bearerAuthorizationExtractor;

    /**
     * 새로운 관리자를 생성합니다.
     *
     * @param request 관리자 생성 요청 정보
     * @return 생성된 관리자 ID
     * @throws AdminAlreadyExistsException 이미 존재하는 로그인 ID일 경우 예외 발생
     */
    public Long createAdmin(AdminCreateRequest request) {
        if (adminRepository.existsByLoginId(request.loginId())) throw new AdminAlreadyExistsException();
        return adminRepository.save(Admin.createAdmin(request.loginId(), passwordEncoder.encode(request.password()))).getId();
    }

    /**
     * 관리자 비밀번호를 수정합니다.
     *
     * @param id      관리자 ID
     * @param request 비밀번호 수정 요청 정보
     * @return 수정된 관리자 ID
     * @throws AdminNotFoundException 관리자 ID가 존재하지 않거나 비밀번호가 일치하지 않을 경우 예외 발생
     */
    public Long updatePassword(Long id, AdminPasswordUpdateRequest request) {
        Admin admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        if (!passwordEncoder.match(admin.getPassword(), request.password())) throw new AdminNotFoundException();
        admin.updatePassword(passwordEncoder.encode(request.password()));
        return admin.getId();
    }

    /**
     * 관리자로 로그인합니다.
     *
     * @param request 로그인 요청 정보
     * @return 로그인 토큰 정보
     * @throws AdminNotFoundException 로그인 ID나 비밀번호가 일치하지 않을 경우 예외 발생
     */
    public MemberTokens login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByLoginId(request.loginId()).orElseThrow(AdminNotFoundException::new);
        if (!passwordEncoder.match(admin.getPassword(), request.password())) throw new AdminNotFoundException();
        MemberTokens memberTokens = jwtProvider.generateLoginToken(admin.getId().toString());
        RefreshToken refreshToken = new RefreshToken(memberTokens.getRefreshToken(), admin.getId());
        refreshTokenRepository.save(refreshToken);
        return memberTokens;
    }

    /**
     * 액세스 토큰을 갱신합니다.
     *
     * @param refreshToken        리프레시 토큰
     * @param authorizationHeader 기존 액세스 토큰
     * @return 갱신된 액세스 토큰
     * @throws RefreshTokenException 리프레시 토큰이 유효하지 않을 경우 예외 발생
     * @throws AuthException         인증 실패 시 예외 발생
     */
    public String renewalAccessToken(String refreshToken, String authorizationHeader) {
        String accessToken = bearerAuthorizationExtractor.extractAccessToken(authorizationHeader);
        if (jwtProvider.isValidRefreshAndInvalidAccess(refreshToken, accessToken)) {
            RefreshToken findRefreshToken = refreshTokenRepository.findById(refreshToken).orElseThrow(RefreshTokenException::new);
            return jwtProvider.regenerateAccessToken(findRefreshToken.getMemberId().toString());
        }
        if (jwtProvider.isValidRefreshAndValidAccess(refreshToken, accessToken)) {
            return accessToken;
        }
        throw new AuthException();
    }

    /**
     * 리프레시 토큰을 제거합니다.
     *
     * @param refreshToken 제거할 리프레시 토큰
     */
    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
