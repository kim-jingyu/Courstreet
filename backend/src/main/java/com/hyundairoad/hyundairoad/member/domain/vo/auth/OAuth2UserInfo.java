package com.hyundairoad.hyundairoad.member.domain.vo.auth;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
