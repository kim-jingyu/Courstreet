package com.hyundairoad.course.domain.dto;

import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * CourseUpdateRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record CourseUpdateRequest(
        @Size(min = 1, max = 50, message = "50자 이하만 입력가능합니다.")
        String title,
        @Size(min = 1, max = 1000, message = "1000자 이하만 입력가능합니다.")
        String content,
        MultipartFile image,
        List<PlacePerMemo> placePerMemos) {
}
