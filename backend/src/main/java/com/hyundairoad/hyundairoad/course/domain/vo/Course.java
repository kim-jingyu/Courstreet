package com.hyundairoad.hyundairoad.course.domain.vo;

import com.hyundairoad.hyundairoad.place.domain.Theme;
import com.hyundairoad.hyundairoad.place.domain.WithWhom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private WithWhom withWhom;
    private Theme mainTheme;
    private Visibility visibility;
    private List<CoursePlace> coursePlaces;
}