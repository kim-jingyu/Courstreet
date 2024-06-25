package com.hyundairoad.member.domain.auth.dto;

import com.hyundairoad.member.domain.Member;
import com.hyundairoad.member.domain.Role;
import lombok.Data;

@Data
public class JoinRequest {
    private String email;
    private String password;
    private String name;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .role(Role.USER)
                .build();
    }
}
