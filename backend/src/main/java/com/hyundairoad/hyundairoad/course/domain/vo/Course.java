package com.hyundairoad.hyundairoad.course.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Long courseId;
    private Long memberId;
    private String title;
    private String content;
    private int startTime;
    private int endTime;
    private String withWhom;
    private String theme;
    private boolean visibility;
    private List<CoursePlace> coursePlaces;
}