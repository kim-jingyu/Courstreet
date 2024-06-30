package com.hyundairoad.login.domain;

import org.springframework.web.client.RestTemplate;

/**
 * OauthProvider
 *
 * 작성자: 김진규
 */
public interface OauthProvider {

    RestTemplate restTemplate = new RestTemplate();

    boolean is(String name);
    OauthUserInfo getUserInfo(String code);
}
