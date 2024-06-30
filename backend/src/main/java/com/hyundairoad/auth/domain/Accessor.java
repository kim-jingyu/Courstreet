package com.hyundairoad.auth.domain;

import lombok.Getter;

/**
 * Accessor
 *
 * 작성자: 김진규
 */
@Getter
public class Accessor {
    private final Long memberId;
    private final Authority authority;

    private Accessor(Long memberId, Authority authority) {
        this.memberId = memberId;
        this.authority = authority;
    }

    public static Accessor guest() {
        return new Accessor(0L, Authority.GUEST);
    }

    public static Accessor member(Long memberId) {
        return new Accessor(memberId, Authority.MEMBER);
    }

    public static Accessor admin(Long memberId) {
        return new Accessor(memberId, Authority.ADMIN);
    }

    public boolean isMember() {
        return Authority.MEMBER.equals(authority);
    }

    public boolean isAdmin() {
        return Authority.ADMIN.equals(authority);
    }
}
