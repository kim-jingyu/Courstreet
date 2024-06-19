package com.hyundairoad.hyundairoad.place.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


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
    private int startAge;
    private int endAge;
    private WithWhom withWhom;
    private int floor;
    private String location;
    private Category category;
    private List<ThemeWeight> themeWeight;
}


