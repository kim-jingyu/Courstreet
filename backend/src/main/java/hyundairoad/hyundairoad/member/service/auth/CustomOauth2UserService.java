package hyundairoad.hyundairoad.member.service.auth;

import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.Role;
import hyundairoad.hyundairoad.member.domain.auth.CustomOauth2UserDetails;
import hyundairoad.hyundairoad.member.domain.auth.GoogleUserDetails;
import hyundairoad.hyundairoad.member.domain.auth.KakaoUserDetails;
import hyundairoad.hyundairoad.member.domain.auth.OAuth2UserInfo;
import hyundairoad.hyundairoad.member.exception.MemberNotFoundException;
import hyundairoad.hyundairoad.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}",oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = null;

        if(provider.equals("google")){
            log.info("구글 로그인");
            oAuth2UserInfo = new GoogleUserDetails(oAuth2User.getAttributes());
        } else if (provider.equals("kakao")) {
            log.info("카카오 로그인");
            oAuth2UserInfo = new KakaoUserDetails(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String loginId = provider + "_" + providerId;
        String name = oAuth2UserInfo.getName();

        Member findMember = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Member member;

        if (findMember == null) {
            member = Member.builder()
                    .email(email)
                    .name(name)
                    .provider(provider)
                    .providerId(providerId)
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        } else{
            member = findMember;
        }

        return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
    }
}
