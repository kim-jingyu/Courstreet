package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoursePlaceDto {
    private Long coursePlaceId;
    private Long courseId;
    private Long imageId;
    private Long placeId;
    private String memo;
    private int rank;
}