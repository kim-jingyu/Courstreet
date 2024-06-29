package com.hyundairoad.admin.domain.dto;

public record PasswordFindRequest(
        String loginId,
        String email

        ) {
}
