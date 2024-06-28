package com.hyundairoad.star.repository;

import com.hyundairoad.star.domain.MemberPlaceStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * MemberPlaceStarRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface MemberPlaceStarRepository extends JpaRepository<MemberPlaceStar, Long> {
    Optional<MemberPlaceStar> findByMemberIdAndPlaceId(Long memberId, Long placeId);

    @Query("SELECT AVG(s.rate) FROM MemberPlaceStar s WHERE s.place.id = :placeId")
    Optional<Double> findAverageRate(Long placeId);
}
