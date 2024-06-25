package com.hyundairoad.mail.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class FindPwRequest {
    private String name;
    @Email
    private String email;
}

