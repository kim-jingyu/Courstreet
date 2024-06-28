package com.hyundairoad.course.domain.dto;

import lombok.Builder;

/**
 * PlacePerMemo
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Builder
public record PlacePerMemo(Long placeId, String memo) {
}
