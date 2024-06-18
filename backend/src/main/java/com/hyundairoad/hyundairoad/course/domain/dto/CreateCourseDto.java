package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseDto {
    private Long courseId;
    private Long memberId;
    private String title;
    private String content;
    private int startTime;
    private int endTime;
    private String withWhom;
    private String theme;
    private Boolean visibility;
    private List<CreateCoursePlaceDto> coursePlaces;
}