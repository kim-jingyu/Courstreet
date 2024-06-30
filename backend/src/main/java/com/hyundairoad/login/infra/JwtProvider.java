package com.hyundairoad.login.infra;

import com.hyundairoad.global.error.ExpiredPeriodJwtException;
import com.hyundairoad.global.error.InvalidJwtException;
import com.hyundairoad.login.domain.MemberTokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JwtProvider
 *
 * 작성자: 김진규
 */
@Component
public class JwtProvider {
    public static final String PREV = "${security.jwt.";

    private final SecretKey secretKey;
    private final Long accessExpirationTime;
    private final Long refreshExpirationTime;

    public JwtProvider(
            @Value(PREV + "secret-key}") String secretKey,
            @Value(PREV + "access-expiration-time}") Long accessExpirationTime,
            @Value(PREV + "refresh-expiration-time}") Long refreshExpirationTime
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public MemberTokens generateLoginToken(String subject) {
        String refreshToken = createToken("", refreshExpirationTime);
        String accessToken = createToken(subject, accessExpirationTime);
        return new MemberTokens(refreshToken, accessToken);
    }

    private String createToken(String subject, Long validityInMilliseconds) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public void validateTokens(MemberTokens memberTokens) {
        validateRefreshToken(memberTokens.getRefreshToken());
        validateAccessToken(memberTokens.getAccessToken());
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            parseToken(refreshToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException();
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException();
        }
    }

    private void validateAccessToken(String accessToken) {
        try {
            parseToken(accessToken);
        } catch (final ExpiredJwtException e) {
            throw new ExpiredPeriodJwtException();
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException();
        }
    }

    public String getSubject(String token) {
        return parseToken(token)
                .getBody()
                .getSubject();
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    public boolean isValidRefreshAndInvalidAccess(String refreshToken, String accessToken) {
        validateRefreshToken(refreshToken);
        try {
            validateAccessToken(accessToken);
        } catch (ExpiredPeriodJwtException e) {
            return true;
        }
        return false;
    }

    public String regenerateAccessToken(String subject) {
        return createToken(subject, accessExpirationTime);
    }

    public boolean isValidRefreshAndValidAccess(String refreshToken, String accessToken) {
        try {
            validateRefreshToken(refreshToken);
            validateAccessToken(accessToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
