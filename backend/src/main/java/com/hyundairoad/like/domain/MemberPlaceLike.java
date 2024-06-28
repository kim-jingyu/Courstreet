package com.hyundairoad.like.domain;

import com.hyundairoad.member.domain.Member;
import com.hyundairoad.place.domain.Place;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

/**
 * 회원-장소 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class MemberPlaceLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;
    @ManyToOne(fetch = LAZY)
    private Place place;

    private int count;

    public static MemberPlaceLike createMemberPlaceLike(Member member, Place place) {
        return MemberPlaceLike.builder()
                .member(member)
                .place(place)
                .count(1)
                .build();
    }

    public void like() {
        count++;
    }

    public void cancelLike() {
        count--;
    }
}
