package com.hyundairoad.hyundairoad.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCourseLike {
    private Long memberCourseLikeId;
    private Long memberId;
    private Long courseId;
}
