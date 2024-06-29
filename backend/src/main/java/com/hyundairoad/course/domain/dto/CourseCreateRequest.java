package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CourseCreateRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record CourseCreateRequest(
        @NotBlank(message = "타이틀을 입력해주세요.")
        @Size(min = 1, max = 50, message = "50자 이하만 입력가능합니다.")
        String title,
        @NotBlank(message = "내용을 입력해주세요")
        @Size(min = 1, max = 1000, message = "1000자 이하만 입력가능합니다.")
        String content,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endTime,
        @NotBlank(message = "테마를 정해주세요.")
        String theme,
        @NotBlank(message = "누구랑 갈지 정해주세요.")
        String withWhom,
        @NotBlank(message = "공개 여부를 결정해주세요.")
        String visibility,
        MultipartFile image,
        List<PlacePerMemo> placePerMemos) {
}
