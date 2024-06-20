package com.hyundairoad.hyundairoad.place.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {
    private Long placeId;
    private String name;
    private String phone;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalDateTime endTime;
    private LocalDateTime startAge;
    private LocalDateTime endAge;
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


