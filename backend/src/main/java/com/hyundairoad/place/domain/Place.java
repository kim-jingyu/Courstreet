package com.hyundairoad.place.domain;

import com.hyundairoad.course.domain.CoursePlace;
import com.hyundairoad.member.domain.WithWhom;
import com.hyundairoad.place.domain.dto.CreatePlaceRequest;
import com.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.Builder.*;

/**
 * 장소 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int startAge;
    @Column(nullable = false)
    private int endAge;
    @Column(nullable = false)
    private int floor;
    @Column(nullable = false)
    private String location;

    private String placeImgUrl;

    @Enumerated(value = EnumType.STRING)
    private WithWhom withWhom;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme1;
    @Column(nullable = false)
    private int weight1;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme2;
    @Column(nullable = false)
    private int weight2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme3;
    @Column(nullable = false)
    private int weight3;

    @Default
    @OneToMany(mappedBy = "place")
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    public static Place createPlace(CreatePlaceRequest createPlaceRequest, String imgUrl) {
        return Place.builder()
                .name(createPlaceRequest.name())
                .phone(createPlaceRequest.phone())
                .startTime(createPlaceRequest.startTime())
                .endTime(createPlaceRequest.endTime())
                .startAge(createPlaceRequest.startAge())
                .endAge(createPlaceRequest.endAge())
                .floor(createPlaceRequest.floor())
                .location(createPlaceRequest.location())
                .placeImgUrl(imgUrl)
                .withWhom(WithWhom.from(createPlaceRequest.withWhom()))
                .category(Category.from(createPlaceRequest.category()))
                .theme1(Theme.from(createPlaceRequest.theme1()))
                .weight1(createPlaceRequest.weight1())
                .theme2(Theme.from(createPlaceRequest.theme2()))
                .weight2(createPlaceRequest.weight2())
                .theme3(Theme.from(createPlaceRequest.theme3()))
                .weight3(createPlaceRequest.weight3()).build();
    }

    public void updatePlace(UpdatePlaceRequest updatePlaceRequest, String imgUrl) {
        this.name = updatePlaceRequest.name();
        this.phone = updatePlaceRequest.phone();
        this.startTime = updatePlaceRequest.startTime();
        this.endTime = updatePlaceRequest.endTime();
        this.floor = updatePlaceRequest.floor();
        this.location = updatePlaceRequest.location();
        this.withWhom = WithWhom.from(updatePlaceRequest.withWhom());
        this.category = Category.from(updatePlaceRequest.category());
        this.theme1 = Theme.from(updatePlaceRequest.theme1());
        this.weight1 = updatePlaceRequest.weight1();
        this.theme2 = Theme.from(updatePlaceRequest.theme2());
        this.weight2 = updatePlaceRequest.weight2();
        this.theme3 = Theme.from(updatePlaceRequest.theme3());
        this.weight3 = updatePlaceRequest.weight3();
        this.placeImgUrl = imgUrl;
    }
}
