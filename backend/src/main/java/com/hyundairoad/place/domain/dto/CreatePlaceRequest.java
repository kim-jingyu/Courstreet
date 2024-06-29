package com.hyundairoad.place.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * CreatePlaceRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record CreatePlaceRequest (
        @NotBlank(message = "장소 이름을 입력해주세요.")
        String name,
        @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8}|\\d{2,3}-\\d{3,4}-\\d{4}|\\d{2,3}\\d{3,4}\\d{4})$", message = "유효한 전화번호를 입력해주세요.")
        String phone,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endTime,
        @NotBlank(message = "시작 나이를 입력해주세요.")
        Integer startAge,
        @NotBlank(message = "종료 나이를 입력해주세요.")
        Integer endAge,
        @NotBlank(message = "층을 입력해주세요.")
        Integer floor,
        @NotBlank(message = "장소를 입력해주세요.")
        String location,
        @NotBlank(message = "누구와 갈지를 입력해주세요.")
        String withWhom,
        @NotBlank(message = "카테고리를 입력해주세요.")
        String category,
        @NotBlank(message = "테마를 입력해주세요.")
        String theme1,
        @NotBlank(message = "기증치를 입력해주세요.")
        Integer weight1,
        @NotBlank(message = "테마를 입력해주세요.")
        String theme2,
        @NotBlank(message = "기증치를 입력해주세요.")
        Integer weight2,
        @NotBlank(message = "테마를 입력해주세요.")
        String theme3,
        @NotBlank(message = "기증치를 입력해주세요.")
        Integer weight3,
        MultipartFile image) {
}
