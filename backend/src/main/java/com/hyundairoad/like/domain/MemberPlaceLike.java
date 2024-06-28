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
                .build();
    }

    public void like() {
        count++;
    }

    public void cancelLike() {
        count--;
    }
}
