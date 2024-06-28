package com.hyundairoad.course.domain;

import com.hyundairoad.course.domain.dto.CourseCreateRequest;
import com.hyundairoad.like.domain.MemberCourseLike;
import com.hyundairoad.member.domain.Member;
import com.hyundairoad.member.domain.WithWhom;
import com.hyundairoad.place.domain.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static lombok.Builder.Default;
import static lombok.EqualsAndHashCode.Include;

/**
 * 코스 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
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
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(value = STRING)
    private Theme theme;
    @Enumerated(value = STRING)
    private WithWhom withWhom;
    @Enumerated(value = STRING)
    private Visibility visibility;

    @Default
    @OneToMany(mappedBy = "course")
    private List<MemberCourseLike> memberCourseLikeList = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "course")
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    public static Course createCourse(Member member, String courseImgUrl, CourseCreateRequest courseCreateRequest, List<CoursePlace> coursePlaces) {
        return Course.builder()
                .member(member)
                .title(courseCreateRequest.title())
                .content(courseCreateRequest.content())
                .courseImgUrl(courseImgUrl)
                .startTime(courseCreateRequest.startTime())
                .endTime(courseCreateRequest.endTime())
                .theme(Theme.from(courseCreateRequest.theme()))
                .withWhom(WithWhom.from(courseCreateRequest.withWhom()))
                .visibility(courseCreateRequest.visibility().equals("Y") ? Visibility.Y : Visibility.N)
                .coursePlaceList(coursePlaces)
                .build();
    }

    public void update(Member member, String title, String content, String newImgUrl) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.courseImgUrl = newImgUrl;
    }
}
