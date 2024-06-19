package com.hyundairoad.hyundairoad.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long memberId;
    private Long imageId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private int age;
    // 성
    // 역할
}
