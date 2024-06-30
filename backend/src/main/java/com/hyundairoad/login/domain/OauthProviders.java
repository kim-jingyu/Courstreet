package com.hyundairoad.login.domain;

import com.hyundairoad.global.error.AuthException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OauthProviders
 *
 * 작성자: 김진규
 */
@Component
public class OauthProviders {

    private final List<OauthProvider> providers;

    public OauthProviders(List<OauthProvider> providers) {
        this.providers = providers;
    }

    public OauthProvider mapping(String providerName) {
        return providers.stream()
                .filter(provider -> provider.is(providerName))
                .findFirst()
                .orElseThrow(AuthException::new);
    }
}
