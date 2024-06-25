package com.hyundairoad.member.domain;

import com.hyundairoad.course.domain.Course;
import jakarta.persistence.*;
import lombok.*;

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
    private String email;
    @Setter
    private String password;
    private String name;
    private String nickName;
    private int age;

    private String profileImgUrl;

    private String provider;
    private String providerId;

    @Enumerated(value = STRING)
    private Gender gender;

    @Enumerated(value = STRING)
    private Role role;

    @Setter
    @Default
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Course> courseList = new ArrayList<>();

    public void changeProfileImg(String newImageUrl) {
        this.profileImgUrl = newImageUrl;
    }

}
