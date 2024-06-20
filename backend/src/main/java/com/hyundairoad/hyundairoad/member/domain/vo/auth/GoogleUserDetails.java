package com.hyundairoad.hyundairoad.member.domain.vo.auth;

import lombok.AllArgsConstructor;

import java.util.Map;

import static com.hyundairoad.hyundairoad.global.constants.AuthCredentials.*;

@AllArgsConstructor
public class GoogleUserDetails implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return GOOGLE;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get(SUB));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get(EMAIL));
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get(NAME));
    }
}
