package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailDto {
    private Long courseId;
    private Long memberId;
    private String memberName;
    private String memberImage;
    private String title;
    private String content;
    private int startTime;
    private int endTime;
    private String withWhom;
    private String theme;
    private Boolean visibility;
    private String courseImage;
    private Long commentId;
    private int commentCount;
    private String commentUserName;
    private String commentUserImage;
    private String commentContent;
    private int likeCount;
    private String liked;
    private List<CoursePlaceDto> coursePlaces;
}