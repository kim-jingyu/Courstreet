package com.hyundairoad.hyundairoad.course.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursePlace {
    private Long coursePlaceId;
    private Long courseId;
    private Long placeId;
    private Long imageId;
    private String memo;
    private int rank;
}
