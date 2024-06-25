package com.hyundairoad.place.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hyundairoad.member.domain.WithWhom;
import lombok.Builder;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
public record PlaceResponse(Long placeId,
                            Long memberId,
                            String name,
                            String phone,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                            LocalDateTime startTime,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                            LocalDateTime endTime,
                            Integer floor,
                            String location,
                            Category category,
                            WithWhom withWhom,
                            List<Map<Theme, Integer>> themes,
                            Resource placeImage,
                            Boolean liked,
                            Double star, // 평균
                            Integer rate) {

    public static PlaceResponse of(Place place, Long memberId, Resource placeImage, Double star, Integer rate) {
        return PlaceResponse.builder()
                .placeId(place.getId())
                .memberId(memberId)
                .phone(place.getPhone())
                .name(place.getName())
                .startTime(place.getStartTime())
                .endTime(place.getEndTime())
                .floor(place.getFloor())
                .location(place.getLocation())
                .category(place.getCategory())
                .withWhom(place.getWithWhom())
                .themes(List.of(
                            Map.of(place.getTheme1(), place.getWeight1()),
                            Map.of(place.getTheme2(), place.getWeight2()),
                            Map.of(place.getTheme3(), place.getWeight3())))
                .placeImage(placeImage)
                .star(star)
                .rate(rate)
                .build();
    }
}
