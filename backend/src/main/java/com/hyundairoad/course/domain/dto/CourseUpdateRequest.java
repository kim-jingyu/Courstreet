package com.hyundairoad.course.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * CourseUpdateRequest
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public record CourseUpdateRequest(Long memberId,
                                  String title,
                                  String content,
                                  MultipartFile image,
                                  List<PlacePerMemo> placePerMemos) {
}
