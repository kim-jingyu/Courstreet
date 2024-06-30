package com.hyundairoad.place.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * UpdatePlaceRequest
 *
 * 작성자: 김진규, 조희정
 */
public record UpdatePlaceRequest(String name,
                                 @Pattern(regexp = "^(010-\\d{4}-\\d{4}|010\\d{8}|\\d{2,3}-\\d{3,4}-\\d{4}|\\d{2,3}\\d{3,4}\\d{4})$", message = "유효한 전화번호를 입력해주세요.")
                                 String phone,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime startTime,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime endTime,
                                 Integer startAge,
                                 Integer endAge,
                                 Integer floor,
                                 String location,
                                 String withWhom,
                                 String category,
                                 String theme1,
                                 Integer weight1,
                                 String theme2,
                                 Integer weight2,
                                 String theme3,
                                 Integer weight3,
                                 MultipartFile image) {
}
