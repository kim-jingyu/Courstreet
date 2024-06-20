package com.hyundairoad.hyundairoad.member.domain.vo.auth;

import lombok.AllArgsConstructor;

import java.util.Map;

import static com.hyundairoad.hyundairoad.global.constants.AuthCredentials.*;

@AllArgsConstructor
public class KakaoUserDetails implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return KAKAO;
    }

    @Override
    public String getProviderId() {
        return attributes.get(ID).toString();
    }

    @Override
    public String getEmail() {
        return ((Map) attributes.get(KAKAO_ACCOUNT)).get(EMAIL).toString();
    }

    @Override
    public String getName() {
        return ((Map) attributes.get(PROPERTIES)).get(NICKNAME).toString();
    }
}
