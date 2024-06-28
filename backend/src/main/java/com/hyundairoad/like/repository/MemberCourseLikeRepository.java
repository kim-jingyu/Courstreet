package com.hyundairoad.like.repository;

import com.hyundairoad.like.domain.MemberCourseLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * MemberCourseLikeRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface MemberCourseLikeRepository extends JpaRepository<MemberCourseLike, Long> {
    Optional<MemberCourseLike> findByMemberIdAndCourseId(Long memberId, Long courseId);
    List<MemberCourseLike> findByMemberId(Long memberId);
}
