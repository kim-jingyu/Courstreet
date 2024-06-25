package com.hyundairoad.member.repository;

import com.hyundairoad.member.domain.like.MemberPlaceLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPlaceLikeRepository extends JpaRepository<MemberPlaceLike, Long> {
    MemberPlaceLike findByMemberIdAndPlaceId(Long memberId, Long placeId);
    List<MemberPlaceLike> findByPlaceId(Long placeId);
    List<MemberPlaceLike> findByMemberId(Long memberId);
}
