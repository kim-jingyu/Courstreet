package com.hyundairoad.hyundairoad.mypage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;
    private int floor;
    private String location;
    private String category;
    private double rate;
    private List<ImageResponseDTO> imageList;

    public static MyPagePlaceResponseDTO of(LikedPlaceDTO likedPlaceDTO, List<ImageResponseDTO> imageList) {
        return MyPagePlaceResponseDTO.builder()
                .name(likedPlaceDTO.getName())
                .phone(likedPlaceDTO.getPhone())
                .rate(likedPlaceDTO.getRate())
                .category(likedPlaceDTO.getCategory())
                .startTime(likedPlaceDTO.getStartTime())
                .endTime(likedPlaceDTO.getEndTime())
                .floor(likedPlaceDTO.getFloor())
                .location(likedPlaceDTO.getLocation())
                .imageList(imageList)
                .build();
    }
}