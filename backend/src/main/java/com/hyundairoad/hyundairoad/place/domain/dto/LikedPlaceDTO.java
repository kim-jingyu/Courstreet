package com.hyundairoad.hyundairoad.place.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundairoad.hyundairoad.place.domain.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikedPlaceDTO {
    private Long placeId;
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
}