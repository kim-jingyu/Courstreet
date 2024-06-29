package com.hyundairoad.admin.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Admin 엔티티
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String loginId;
    @Column(nullable = false, length = 30)
    private String password;
    private LocalDateTime lastLoginDate;

    public Admin(Long id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.lastLoginDate = LocalDateTime.now();
    }

    @Builder
    public Admin(String loginId, String password) {
        this(null, loginId, password);
    }

    public static Admin createAdmin(String loginId, String password) {
        return Admin.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
