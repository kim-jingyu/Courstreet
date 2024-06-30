package com.hyundairoad.login.infra.oauthprovider;

import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.login.domain.OauthAccessToken;
import com.hyundairoad.login.domain.OauthProvider;
import com.hyundairoad.login.domain.OauthUserInfo;
import com.hyundairoad.login.infra.oauthuserinfo.KakaoUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

/**
 * KakaoOauthProvider
 *
 * 작성자: 김진규
 */
@Component
public class KakaoOauthProvider implements OauthProvider {
    private static final String PREV = "${oauth2.provider.kakao.";
    private static final String PROVIDER_NAME = "kakao";
    private static final String SECURE_RESOURCE = "secure_resource";

    protected final String clientId;
    protected final String clientSecret;
    protected final String redirectUri;
    protected final String tokenUri;
    protected final String userUri;

    public KakaoOauthProvider(
            @Value(PREV + "client-id}") String clientId,
            @Value(PREV + "client-secret}") String clientSecret,
            @Value(PREV + "redirect-uri}") String redirectUri,
            @Value(PREV + "token-uri}") String tokenUri,
            @Value(PREV + "user-info}") String userUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenUri = tokenUri;
        this.userUri = userUri;
    }

    @Override
    public boolean is(String name) {
        return PROVIDER_NAME.equals(name);
    }

    @Override
    public OauthUserInfo getUserInfo(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(requestAccessToken(code));
        HttpEntity<MultiValueMap<String, String>> userInfoRequestEntity = new HttpEntity<>(headers);

        Map<String, Boolean> queryParam = new HashMap<>();
        queryParam.put(SECURE_RESOURCE, TRUE);

        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                userUri,
                HttpMethod.GET,
                userInfoRequestEntity,
                KakaoUserInfo.class,
                queryParam
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new AuthException();
    }

    private String requestAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");
        HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<OauthAccessToken> accessTokenResponse = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                accessTokenRequestEntity,
                OauthAccessToken.class
        );

        return Optional.ofNullable(accessTokenResponse.getBody())
                .orElseThrow(AuthException::new)
                .getAccessToken();
    }
}
