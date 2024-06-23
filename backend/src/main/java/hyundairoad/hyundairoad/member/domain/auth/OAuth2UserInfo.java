package hyundairoad.hyundairoad.member.domain.auth;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
