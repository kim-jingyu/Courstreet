package com.hyundairoad.login.domain;

/**
 * OauthProviders
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface OauthUserInfo {
    String getSocialLoginId();
    String getNickname();
    String getImageUrl();
}
