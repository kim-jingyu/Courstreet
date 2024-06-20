package com.hyundairoad.hyundairoad.place.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Place {
    private Long placeId;
    private String name;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;
    private int startAge;
    private int endAge;
    private String withWhom;
    private int floor;
    private String location;
    private String category;
    private String theme1;
    private String theme2;
    private String theme3;
    private int weight1;
    private int weight2;
    private int weight3;
}


