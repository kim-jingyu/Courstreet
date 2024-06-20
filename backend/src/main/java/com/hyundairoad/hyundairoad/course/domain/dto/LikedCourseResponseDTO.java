package com.hyundairoad.hyundairoad.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedCourseResponseDTO {
    private Long courseId;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long placeId;
    private Long imageId;
    private String memo;
    private Integer rank;
}
