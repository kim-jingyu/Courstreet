package com.hyundairoad.hyundairoad.member.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginDTO {
    @Email
    private String Email;
    @Min(value = 8)
    private String password;
}
