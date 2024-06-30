package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * CourseRecommendRequest
 *
 * 작성자: 남진수
 */
public record CourseRecommendRequest(Long memberId,
                                     String withWhom,
                                     String theme,
                                     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
                                     LocalDateTime startTime,
                                     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
                                     LocalDateTime endTime,
                                     String favoritePlace,
                                     String gender,
                                     Integer age) {
}
