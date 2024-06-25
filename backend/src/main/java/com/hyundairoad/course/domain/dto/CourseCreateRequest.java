package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record CourseCreateRequest(Long memberId,
                                  String title,
                                  String content,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                  LocalDate startTime,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                  LocalDate endTime,
                                  String theme,
                                  String withWhom,
                                  String visibility,
                                  MultipartFile image,
                                  List<PlacePerMemo> placePerMemos) {
}
