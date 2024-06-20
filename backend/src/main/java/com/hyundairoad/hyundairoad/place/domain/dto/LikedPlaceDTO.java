package com.hyundairoad.hyundairoad.place.domain.dto;

import com.hyundairoad.hyundairoad.place.domain.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikedPlaceDTO {
    private Long placeId;
    private String name;
    private Integer star;
    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int floor;
}
