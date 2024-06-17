package com.hyundairoad.hyundairoad.place.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Place {
    private Long placeId;
    private String name;
    private String phone;
    private Date startTime;
    private Date endTime;
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
