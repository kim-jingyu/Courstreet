package com.hyundairoad.mypage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundairoad.place.domain.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPagePlaceResponse {
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
    private Resource imageList;

    public static MyPagePlaceResponse of(PlaceResponse placeResponse) {
        return MyPagePlaceResponse.builder()
                .name(placeResponse.name())
                .phone(placeResponse.phone())
                .rate(placeResponse.rate())
                .category(placeResponse.category().getValue())
                .startTime(placeResponse.startTime())
                .endTime(placeResponse.endTime())
                .floor(placeResponse.floor())
                .location(placeResponse.location())
                .imageList(placeResponse.placeImage())
                .build();
    }
}
