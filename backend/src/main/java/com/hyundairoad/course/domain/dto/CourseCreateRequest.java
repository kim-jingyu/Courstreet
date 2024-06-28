package com.hyundairoad.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public record CourseCreateRequest(Long memberId,
                                  String title,
                                  String content,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                                  @Schema(description = "기간 시작일", example = "2021-11-30 00:00:00", type = "string")
                                  LocalDateTime startTime,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                                  @Schema(description = "기간 시작일", example = "2021-11-30 00:00:00", type = "string")
                                  LocalDateTime endTime,
                                  String theme,
                                  String withWhom,
                                  String visibility,
                                  @Schema(type = "string", format = "binary")
                                  MultipartFile image,
                                  List<PlacePerMemo> placePerMemos) {
}
