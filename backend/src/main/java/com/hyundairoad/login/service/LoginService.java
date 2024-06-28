package com.hyundairoad.login.service;

import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.login.domain.*;
import com.hyundairoad.login.domain.repository.RefreshTokenRepository;
import com.hyundairoad.login.infra.BearerAuthorizationExtractor;
import com.hyundairoad.login.infra.JwtProvider;
import com.hyundairoad.member.domain.Member;
import com.hyundairoad.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 로그인 서비스
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private static final int MAX_TRY_COUNT = 5;
    private static final int FOUR_DIGIT_RANGE = 10000;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final OauthProviders oauthProviders;
    private final JwtProvider jwtProvider;
    private final BearerAuthorizationExtractor bearerExtractor;

    /**
     * 소셜 로그인 요청을 처리합니다.
     *
     * @param providerName 소셜 로그인 제공자 이름
     * @param code 소셜 로그인 인증 코드
     * @return 회원 토큰 정보
     */
    public MemberTokens login(String providerName, String code) {
        OauthProvider provider = oauthProviders.mapping(providerName);
        OauthUserInfo oauthUserInfo = provider.getUserInfo(code);
        Member member = findOrCreateMember(oauthUserInfo.getSocialLoginId(), oauthUserInfo.getNickname(), oauthUserInfo.getImageUrl());
        MemberTokens memberTokens = jwtProvider.generateLoginToken(member.getId().toString());
        refreshTokenRepository.save(new RefreshToken(memberTokens.getRefreshToken(), member.getId()));
        return memberTokens;
    }

    /**
     * 소셜 로그인 ID를 기반으로 회원을 찾거나 생성합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @param nickname 회원 닉네임
     * @param imageUrl 회원 프로필 이미지 URL
     * @return 회원 객체
     */
    private Member findOrCreateMember(String socialLoginId, String nickname, String imageUrl) {
        return memberRepository.findBySocialLoginId(socialLoginId).orElseGet(() -> createMember(socialLoginId, nickname, imageUrl));
    }

    /**
     * 새로운 회원을 생성합니다.
     *
     * @param socialLoginId 소셜 로그인 ID
     * @param nickname 회원 닉네임
     * @param imageUrl 회원 프로필 이미지 URL
     * @return 생성된 회원 객체
     */
    private Member createMember(String socialLoginId, String nickname, String imageUrl) {
        int tryCount = 0;
        while (tryCount < MAX_TRY_COUNT) {
            String nicknameWithRandomNumber = nickname + generateRandomFourDigitCode();
            if (!memberRepository.existsByNickname(nicknameWithRandomNumber)) {
                return memberRepository.save(new Member(socialLoginId, nicknameWithRandomNumber, imageUrl));
            }
            tryCount += 1;
        }
        throw new AuthException();
    }

    /**
     * 4자리 무작위 숫자 코드를 생성합니다.
     *
     * @return 4자리 숫자 문자열
     */
    private String generateRandomFourDigitCode() {
        return String.format("%04d", (int) (Math.random() * FOUR_DIGIT_RANGE));
    }

    /**
     * 액세스 토큰을 갱신합니다.
     *
     * @param refreshTokenRequest 리프레시 토큰
     * @param authorizationHeader 기존 액세스 토큰
     * @return 갱신된 액세스 토큰
     */
    public String renewalAccessToken(String refreshTokenRequest, String authorizationHeader) {
        String accessToken = bearerExtractor.extractAccessToken(authorizationHeader);
        if (jwtProvider.isValidRefreshAndInvalidAccess(refreshTokenRequest, accessToken)) {
            RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenRequest).orElseThrow(AuthException::new);
            return jwtProvider.regenerateAccessToken(refreshToken.getMemberId().toString());
        }
        if (jwtProvider.isValidRefreshAndValidAccess(refreshTokenRequest, accessToken)) {
            return accessToken;
        }
        throw new AuthException();
    }

    /**
     * 리프레시 토큰을 제거합니다.
     *
     * @param refreshToken 리프레시 토큰
     */
    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }
}
