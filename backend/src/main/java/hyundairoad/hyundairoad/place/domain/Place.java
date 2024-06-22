package hyundairoad.hyundairoad.place.domain;

import hyundairoad.hyundairoad.course.domain.CoursePlace;
import hyundairoad.hyundairoad.member.domain.WithWhom;
import hyundairoad.hyundairoad.place.domain.dto.CreatePlaceRequest;
import hyundairoad.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int startAge;
    private int endAge;
    private int floor;
    private String location;

    private String placeImgUrl;

    @Enumerated(value = EnumType.STRING)
    private WithWhom withWhom;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Theme theme1;
    private int weight1;

    @Enumerated(EnumType.STRING)
    private Theme theme2;
    private int weight2;

    @Enumerated(EnumType.STRING)
    private Theme theme3;
    private int weight3;

    @Builder.Default
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
