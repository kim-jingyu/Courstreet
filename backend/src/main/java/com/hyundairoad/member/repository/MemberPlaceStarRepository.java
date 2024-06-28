package com.hyundairoad.member.repository;

import com.hyundairoad.star.MemberPlaceStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberPlaceStarRepository extends JpaRepository<MemberPlaceStar, Long> {
    List<MemberPlaceStar> findByPlaceId(Long placeId);
    MemberPlaceStar findByPlaceIdAndMemberId(Long placeId, Long memberId);
    @Query("SELECT AVG(m.rate) FROM MemberPlaceStar m WHERE m.place.id = :placeId")
    Double findAverageRate(Long placeId);
}
