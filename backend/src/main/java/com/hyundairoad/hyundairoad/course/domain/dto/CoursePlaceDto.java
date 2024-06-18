package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlaceDto {
    private Long coursePlaceId;
    private Long courseId;
    private Long placeId;
    private String memo;
    private int rank;
    private String image;
}
