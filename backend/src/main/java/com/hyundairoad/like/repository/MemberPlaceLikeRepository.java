package com.hyundairoad.like.repository;

import com.hyundairoad.like.domain.MemberPlaceLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * MemberPlaceLikeRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface MemberPlaceLikeRepository extends JpaRepository<MemberPlaceLike, Long> {
    Optional<MemberPlaceLike> findByMemberIdAndPlaceId(Long memberId, Long placeId);
    List<MemberPlaceLike> findByPlaceId(Long placeId);
    List<MemberPlaceLike> findByMemberId(Long memberId);
}
