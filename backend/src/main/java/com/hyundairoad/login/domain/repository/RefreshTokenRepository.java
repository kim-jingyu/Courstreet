package com.hyundairoad.login.domain.repository;

import com.hyundairoad.login.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

/**
 * RefreshTokenRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
