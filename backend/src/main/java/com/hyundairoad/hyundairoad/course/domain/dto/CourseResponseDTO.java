package com.hyundairoad.hyundairoad.course.domain.dto;

import com.hyundairoad.hyundairoad.course.domain.vo.CoursePlace;
import com.hyundairoad.hyundairoad.course.domain.vo.Visibility;
import com.hyundairoad.hyundairoad.place.domain.Theme;
import com.hyundairoad.hyundairoad.place.domain.WithWhom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    private Long courseId;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private WithWhom withWhom;
    private Theme mainTheme;
    private Visibility visibility;
    private String imgOriginalName;
    private String imgSavedName;
    private List<CoursePlace> coursePlaces;
}
