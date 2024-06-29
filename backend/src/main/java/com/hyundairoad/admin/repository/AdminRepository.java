package com.hyundairoad.admin.repository;

import com.hyundairoad.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * AdminRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Boolean existsByLoginId(String loginId);
    Optional<Admin> findByLoginId(String loginId);
}
