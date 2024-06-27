package com.hyundairoad.login.infra.oauthuserinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyundairoad.login.domain.OauthUserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class KakaoUserInfo implements OauthUserInfo {

    @JsonProperty("id")
    private String socialLoginId;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Override
    public String getSocialLoginId() {
        return socialLoginId;
    }

    @Override
    public String getNickname() {
        return kakaoAccount.kakaoProfile.nickname;
    }

    @Override
    public String getImageUrl() {
        return kakaoAccount.kakaoProfile.image;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoAccount {

        @JsonProperty("profile")
        private KakaoProfile kakaoProfile;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoProfile {

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image_url")
        private String image;
    }
}
