package com.hyundairoad.login.domain;

/**
 * OauthProviders
 *
 * 작성자: 김진규
 */
public interface OauthUserInfo {
    String getSocialLoginId();
    String getNickname();
    String getImageUrl();
}
