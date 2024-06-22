package hyundairoad.hyundairoad.course.domain;

import hyundairoad.hyundairoad.course.domain.dto.CourseCreateRequest;
import hyundairoad.hyundairoad.member.domain.Member;
import hyundairoad.hyundairoad.member.domain.WithWhom;
import hyundairoad.hyundairoad.place.domain.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static lombok.EqualsAndHashCode.Include;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Course {
    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    private String courseImgUrl;

    private String title;
    @Lob
    private String content;
    private LocalDate startTime;
    private LocalDate endTime;
    @Enumerated(value = STRING)
    private Theme theme;
    @Enumerated(value = STRING)
    private WithWhom withWhom;
    @Enumerated(value = STRING)
    private Visibility visibility;

    @OneToMany(mappedBy = "course")
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    public static Course createCourse(Member member, String courseImgUrl, CourseCreateRequest courseCreateRequest, List<CoursePlace> coursePlaces) {
        Course course = Course.builder()
                .member(member)
                .title(courseCreateRequest.title())
                .content(courseCreateRequest.content())
                .courseImgUrl(courseImgUrl)
                .startTime(courseCreateRequest.startTime())
                .endTime(courseCreateRequest.endTime())
                .theme(Theme.from(courseCreateRequest.theme()))
                .withWhom(WithWhom.from(courseCreateRequest.withWhom()))
                .visibility(courseCreateRequest.visibility().equals("Y") ? Visibility.Y : Visibility.N)
                .build();
        course.coursePlaceList.addAll(coursePlaces);
        return course;
    }

    public void update(Member member, String title, String content, String newImgUrl) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.courseImgUrl = newImgUrl;
    }
}
