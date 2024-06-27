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
import static lombok.EqualsAndHashCode.Include;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString
public class Member {
    @Id
    @Include
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
    private Role role;

    @Setter
    @Default
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Course> courseList = new ArrayList<>();

    public Member(final Long id, final String socialLoginId, final String nickName, final String imageUrl) {
        this.id = id;
        this.socialLoginId = socialLoginId;
        this.nickname = nickName;
        this.lastLoginDate = LocalDateTime.now();
        this.imageUrl = imageUrl;
    }

    public Member(final String socialLoginId, final String nickName, final String imageUrl) {
        this(null, socialLoginId, nickName, imageUrl);
    }

    public boolean isNicknameChanged(final String inputNickname) {
        return !nickname.equals(inputNickname);
    }

    public void changeProfileImg(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }

}
