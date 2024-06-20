package com.hyundairoad.hyundairoad.mypage.domain.dto;

import com.hyundairoad.hyundairoad.image.domain.dto.ImageResponseDTO;
import com.hyundairoad.hyundairoad.place.domain.Category;
import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPagePlaceResponseDTO {
    private String name;
    private Double star;
    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int floor;
    private List<ImageResponseDTO> imageList;

    public static MyPagePlaceResponseDTO of(LikedPlaceDTO likedPlaceDTO, List<ImageResponseDTO> imageList) {
        return MyPagePlaceResponseDTO.builder()
                .name(likedPlaceDTO.getName())
                .star(likedPlaceDTO.getStar())
                .category(likedPlaceDTO.getCategory())
                .startTime(likedPlaceDTO.getStartTime())
                .endTime(likedPlaceDTO.getEndTime())
                .floor(likedPlaceDTO.getFloor())
                .imageList(imageList)
                .build();
    }
}
