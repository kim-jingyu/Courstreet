package com.hyundairoad.hyundairoad.image.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Long imageId;
    private Long memberId;
    private Long placeId;
    private Long coursePlaceId;
    private Long courseId;
    private String savedName;
    private String originalName;
}
