package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundairoad.course.domain.Course;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CourseResponse(Long courseId,
                             Long memberId,
                             String nickname,
                             String title,
                             String content,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime startTime,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime endTime,
                             String theme,
                             String withWhom,
                             String visibility,
                             String courseImage,
                             Boolean liked) {

    public static CourseResponse of(Course course) {
        return CourseResponse.builder()
                .courseId(course.getId())
                .title(course.getTitle())
                .content(course.getContent())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .theme(course.getTheme().getValue())
                .withWhom(course.getWithWhom().getValue())
                .visibility(course.getVisibility().name())
                .courseImage(course.getCourseImgUrl())
                .build();
    }

    public static CourseResponse of(Course course, boolean liked) {
        return CourseResponse.builder()
                .courseId(course.getId())
                .title(course.getTitle())
                .content(course.getContent())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .theme(course.getTheme().getValue())
                .withWhom(course.getWithWhom().getValue())
                .visibility(course.getVisibility().name())
                .courseImage(course.getCourseImgUrl())
                .liked(liked)
                .build();
    }

    public static CourseResponse of(Course course, String nickname, Long memberId) {
        return CourseResponse.builder()
                .courseId(course.getId())
                .memberId(memberId)
                .nickname(nickname)
                .title(course.getTitle())
                .content(course.getContent())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .theme(course.getTheme().getValue())
                .withWhom(course.getWithWhom().getValue())
                .visibility(course.getVisibility().name())
                .courseImage(course.getCourseImgUrl())
                .build();
    }

    public static CourseResponse of(Course course, String nickname, Long memberId, Boolean liked) {
        return CourseResponse.builder()
                .courseId(course.getId())
                .memberId(memberId)
                .nickname(nickname)
                .title(course.getTitle())
                .content(course.getContent())
                .startTime(course.getStartTime())
                .endTime(course.getEndTime())
                .theme(course.getTheme().getValue())
                .withWhom(course.getWithWhom().getValue())
                .visibility(course.getVisibility().name())
                .courseImage(course.getCourseImgUrl())
                .liked(liked)
                .build();
    }
}
