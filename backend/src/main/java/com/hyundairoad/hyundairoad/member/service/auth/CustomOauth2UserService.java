package com.hyundairoad.hyundairoad.member.service.auth;

import com.hyundairoad.hyundairoad.member.domain.vo.Member;
import com.hyundairoad.hyundairoad.member.domain.vo.Role;
import com.hyundairoad.hyundairoad.member.domain.vo.auth.CustomOauth2UserDetails;
import com.hyundairoad.hyundairoad.member.domain.vo.auth.GoogleUserDetails;
import com.hyundairoad.hyundairoad.member.domain.vo.auth.KakaoUserDetails;
import com.hyundairoad.hyundairoad.member.domain.vo.auth.OAuth2UserInfo;
import com.hyundairoad.hyundairoad.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.hyundairoad.hyundairoad.global.constants.AuthCredentials.GOOGLE;
import static com.hyundairoad.hyundairoad.global.constants.AuthCredentials.KAKAO;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes = {}", oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();
        log.info("provider = {}", provider);

        OAuth2UserInfo oAuth2UserInfo = null;

        // FOR GOOGLE
        if (provider.equals(GOOGLE)) {
            log.info("구글 간편인증");
            oAuth2UserInfo = new GoogleUserDetails(oAuth2User.getAttributes());
        } else if (provider.equals(KAKAO)) {
            log.info("카카오 간편인증");
            oAuth2UserInfo = new KakaoUserDetails(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String loginId = provider + "_" + providerId;
        String name = oAuth2UserInfo.getName();

        Member findMember = memberMapper.getMemberByLoginId(loginId);
        Member member;

        // 첫 로그인이면 자동으로 회원가입 진행
        if (findMember == null) {
            member = Member.builder()
                    .loginId(loginId)
                    .email(email)
                    .name(name)
                    .provider(provider)
                    .providerId(providerId)
                    .role(Role.NORMAL)
                    .build();
            memberMapper.save(member);
        } else {
            member = findMember;
        }

        return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
    }
}
