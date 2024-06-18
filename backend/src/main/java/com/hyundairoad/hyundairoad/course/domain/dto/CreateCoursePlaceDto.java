package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCoursePlaceDto {
    private Long coursePlaceId;
    private Long courseId;
    private Long placeId;
    private String memo;
    private int rank;
    private String image;
}
