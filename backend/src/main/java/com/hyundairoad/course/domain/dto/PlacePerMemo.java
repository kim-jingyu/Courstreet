package com.hyundairoad.course.domain.dto;

import lombok.Builder;

/**
 * PlacePerMemo
 *
 * 작성자: 김진규, 남진수
 */
@Builder
public record PlacePerMemo(Long placeId, String memo) {
}
