package com.hyundairoad.like.domain;

import com.hyundairoad.course.domain.Course;
import com.hyundairoad.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

/**
 * 회원-코스 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberCourseLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;
    @ManyToOne(fetch = LAZY)
    private Course course;

    private int count;

    public static MemberCourseLike createMemberCourseLike(Member member, Course course) {
        return MemberCourseLike.builder()
                .member(member)
                .course(course)
                .build();
    }

    public void like() {
        count++;
    }

    public void cancelLike() {
        count--;
    }
}
