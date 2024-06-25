package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundairoad.course.domain.Course;
import lombok.Builder;
import org.springframework.core.io.Resource;

import java.time.LocalDate;

@Builder
public record CourseResponse(Long courseId,
                             Long memberId,
                             String nickname,
                             String title,
                             String content,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                             LocalDate startTime,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                             LocalDate endTime,
                             String theme,
                             String withWhom,
                             String visibility,
                             Resource courseImage,
                             Boolean liked) {

    public static CourseResponse of(Course course, String nickname, Long memberId, Resource courseImage, Boolean liked) {
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
                .courseImage(courseImage)
                .liked(liked)
                .build();
    }
}
