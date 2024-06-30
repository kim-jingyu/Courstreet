package com.hyundairoad.star.domain;

import com.hyundairoad.member.domain.Member;
import com.hyundairoad.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

/**
 * 회원-장소 별점 엔티티
 *
 * 작성자: 김진규
 */
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPlaceStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;
    @ManyToOne(fetch = LAZY)
    private Place place;

    private Double rate;

    public static MemberPlaceStar createMemberPlaceStar(Member member, Place place) {
        return MemberPlaceStar.builder()
                .member(member)
                .place(place)
                .build();
    }

    public void updateRate(Double rate) {
        this.rate = rate;
    }
}
