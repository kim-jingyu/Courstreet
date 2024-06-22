package hyundairoad.hyundairoad.course.domain;

import hyundairoad.hyundairoad.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CoursePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coursePlaceImageUrl;
    @Lob
    private String memo;
    private int rank;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false)
    private Place place;

    public static CoursePlace create (Place place, String memo) {
        return CoursePlace.builder()
                .place(place)
                .memo(memo)
                .build();
    }

    public void update(Place place, String memo) {
        this.place = place;
        this.memo = memo;
    }
}
