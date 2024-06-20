package com.hyundairoad.hyundairoad.image.domain.dto;

import com.hyundairoad.hyundairoad.image.domain.vo.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDTO {
    private String originalName;
    private String savedName;

    public static ImageResponseDTO of(Image image) {
        return ImageResponseDTO.builder()
                .originalName(image.getOriginalName())
                .savedName(image.getSavedName())
                .build();
    }
}
