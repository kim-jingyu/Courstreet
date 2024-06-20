package com.hyundairoad.hyundairoad.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLikedResponseDTO {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private int age;
}
