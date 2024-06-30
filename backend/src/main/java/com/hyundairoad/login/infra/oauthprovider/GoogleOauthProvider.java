package com.hyundairoad.login.infra.oauthprovider;

import com.hyundairoad.global.error.AuthException;
import com.hyundairoad.login.domain.OauthAccessToken;
import com.hyundairoad.login.domain.OauthProvider;
import com.hyundairoad.login.domain.OauthUserInfo;
import com.hyundairoad.login.infra.oauthuserinfo.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

/**
 * GoogleOauthProvider
 *
 * 작성자: 김진규
 */
@Component
public class GoogleOauthProvider implements OauthProvider {
    private static final String PREV = "${oauth2.provider.google.";
    private static final String PROVIDER_NAME = "google";

    protected final String clientId;
    protected final String clientSecret;
    protected final String redirectUri;
    protected final String tokenUri;
    protected final String userUri;

    public GoogleOauthProvider(
            @Value(PREV + "client-id}") String clientId,
            @Value(PREV + "client-secret}") String clientSecret,
            @Value(PREV + "redirect-uri}") String redirectUri,
            @Value(PREV + "token-uri}")  String tokenUri,
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

        ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(
                userUri,
                HttpMethod.GET,
                userInfoRequestEntity,
                GoogleUserInfo.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new AuthException();
    }

    private String requestAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(clientId, clientSecret);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = new HttpEntity<>(params, httpHeaders);
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
