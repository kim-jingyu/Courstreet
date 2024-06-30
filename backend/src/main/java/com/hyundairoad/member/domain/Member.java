package com.hyundairoad.member.domain;

import com.hyundairoad.course.domain.Course;
import com.hyundairoad.like.domain.MemberCourseLike;
import com.hyundairoad.like.domain.MemberPlaceLike;
import com.hyundairoad.star.domain.MemberPlaceStar;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.hyundairoad.member.domain.MemberStatus.ACTIVE;
import static com.hyundairoad.member.domain.MemberStatus.DELETED;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static lombok.Builder.Default;

/**
 * 회원 엔티티
 *
 * 작성자: 김진규
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String socialLoginId;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;
    @Column(nullable = false)
    private LocalDateTime lastLoginDate;

    private String imageUrl;

    @Enumerated(value = STRING)
    private Gender gender;

    @Enumerated(value = STRING)
    private MemberStatus status;

    @Default
    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Course> courseList = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MemberCourseLike> memberCourseLikeList = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MemberPlaceLike> memberPlaceLikeList = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MemberPlaceStar> memberPlaceStarList = new ArrayList<>();

    public Member(String socialLoginId, String nickName, String imageUrl) {
        this.socialLoginId = socialLoginId;
        this.nickname = nickName;
        this.lastLoginDate = LocalDateTime.now();
        this.imageUrl = imageUrl;
        this.status = ACTIVE;
    }

    public void changeProfileImg(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }

    public void changeStatus() {
        this.status = DELETED;
    }
}
