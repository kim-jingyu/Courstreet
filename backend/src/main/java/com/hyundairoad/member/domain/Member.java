package com.hyundairoad.member.domain;

import com.hyundairoad.course.domain.Course;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static lombok.Builder.Default;

/**
 * 회원 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
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

    @Setter
    @Default
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Course> courseList = new ArrayList<>();

    public Member(String socialLoginId, String nickName, String imageUrl) {
        this.socialLoginId = socialLoginId;
        this.nickname = nickName;
        this.lastLoginDate = LocalDateTime.now();
        this.imageUrl = imageUrl;
    }

    public boolean isNicknameChanged(String inputNickname) {
        return !nickname.equals(inputNickname);
    }

    public void changeProfileImg(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }

}
