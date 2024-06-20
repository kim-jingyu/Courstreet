package com.hyundairoad.hyundairoad.mypage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageLikedCourseResponseDTO {
    private Long courseId;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
