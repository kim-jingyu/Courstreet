package com.hyundairoad.member.repository;

import com.hyundairoad.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * MemberRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickName);
    Optional<Member> findBySocialLoginId(String socialLoginId);
}
