package com.hyundairoad.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * RefreshToken
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 604800)
public class RefreshToken {

    @Id
    private String token;

    private Long memberId;
}
