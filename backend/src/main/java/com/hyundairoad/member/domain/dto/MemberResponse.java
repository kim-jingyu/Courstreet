package com.hyundairoad.member.domain.dto;

import com.hyundairoad.member.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * MemberResponse
 *
 * 작성자: 김진규
 */
@Data
@Builder
public class MemberResponse {
    private Long id;
    private String socialLoginId;
    private String nickname;
    private LocalDateTime lastLoginDate;
    private String imageUrl;
    private String gender;
    private String status;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .socialLoginId(member.getSocialLoginId())
                .nickname(member.getNickname())
                .lastLoginDate(member.getLastLoginDate())
                .imageUrl(member.getImageUrl())
                .gender(member.getGender().name())
                .status(member.getStatus().name())
                .build();
    }
}
