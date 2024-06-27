package com.hyundairoad.login.domain.repository;

import com.hyundairoad.login.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
