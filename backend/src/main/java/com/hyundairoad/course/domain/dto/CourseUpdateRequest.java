package com.hyundairoad.course.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CourseUpdateRequest(Long memberId,
                                  String title,
                                  String content,
                                  MultipartFile image,
                                  List<PlacePerMemo> placePerMemos) {
}
