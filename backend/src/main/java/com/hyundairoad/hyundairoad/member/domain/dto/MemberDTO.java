package com.hyundairoad.hyundairoad.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
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
