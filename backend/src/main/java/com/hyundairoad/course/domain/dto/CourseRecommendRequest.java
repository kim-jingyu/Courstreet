package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

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
