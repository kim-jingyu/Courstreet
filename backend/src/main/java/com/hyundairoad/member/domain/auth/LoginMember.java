package com.hyundairoad.member.domain.auth;

import com.hyundairoad.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.hyundairoad.member.domain.Role.*;

@Getter
@RequiredArgsConstructor
@Builder
public class LoginMember {
    private final Long memberId;
    private final Role role;

    public static LoginMember user(Long memberId) {
        return LoginMember.builder()
                .memberId(memberId)
                .role(USER)
                .build();
    }

    public static LoginMember admin(Long memberId) {
        return LoginMember.builder()
                .memberId(memberId)
                .role(ADMIN)
                .build();
    }

    public boolean isUser() {
        return this.role.equals(USER);
    }

    public boolean isAdmin() {
        return this.role.equals(ADMIN);
    }
}
