package com.hyundairoad.hyundairoad.place.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
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
    private String withWhom;
    private int floor;
    private String location;
    private String type;
    private String category;
    private String theme1;
    private String theme2;
    private String theme3;
    private int weight1;
    private int weight2;
    private int weight3;
}


