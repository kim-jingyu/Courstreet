package com.hyundairoad.login.domain;

import com.hyundairoad.global.error.AuthException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OauthProviders {

    private final List<OauthProvider> providers;

    public OauthProviders(final List<OauthProvider> providers) {
        this.providers = providers;
    }

    public OauthProvider mapping(final String providerName) {
        return providers.stream()
                .filter(provider -> provider.is(providerName))
                .findFirst()
                .orElseThrow(AuthException::new);
    }
}
