package com.hyundairoad.hyundairoad.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPlaceStar {
    private Long memberPlaceStarId;
    private Long memberId;
    private Long placeId;
    private Double rate;
}
