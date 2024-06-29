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
 * 작성일: 2024-06-29
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

    public Long createAdmin(AdminCreateRequest request) {
        if (adminRepository.existsByLoginId(request.loginId())) throw new AdminAlreadyExistsException();
        return adminRepository.save(Admin.createAdmin(request.loginId(), passwordEncoder.encode(request.password()))).getId();
    }

    public Long updatePassword(Long id, AdminPasswordUpdateRequest request) {
        Admin admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        if (!passwordEncoder.match(admin.getPassword(), request.password())) throw new AdminNotFoundException();
        admin.updatePassword(passwordEncoder.encode(request.password()));
        return admin.getId();
    }

    public MemberTokens login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByLoginId(request.loginId()).orElseThrow(AdminNotFoundException::new);
        if (!passwordEncoder.match(admin.getPassword(), request.password())) throw new AdminNotFoundException();
        MemberTokens memberTokens = jwtProvider.generateLoginToken(admin.getId().toString());
        RefreshToken refreshToken = new RefreshToken(memberTokens.getRefreshToken(), admin.getId());
        refreshTokenRepository.save(refreshToken);
        return memberTokens;
    }

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

    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
