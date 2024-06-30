package com.hyundairoad.admin.repository;

import com.hyundairoad.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * AdminRepository
 *
 * 작성자: 김진규
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Boolean existsByLoginId(String loginId);
    Optional<Admin> findByLoginId(String loginId);
}
