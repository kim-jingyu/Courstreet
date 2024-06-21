package com.hyundairoad.hyundairoad.course.domain.dto;

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
public class CourseDetailDto {
    private Long courseId;
    private Long memberId;
    private Long imageId;
    private String memberName;
    private String memberImage;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String withWhom;
    private String theme;
    private Boolean visibility;
    private String courseImage;
    private int likeCount;
    private String liked;
    private List<CoursePlaceDto> coursePlaces;
}