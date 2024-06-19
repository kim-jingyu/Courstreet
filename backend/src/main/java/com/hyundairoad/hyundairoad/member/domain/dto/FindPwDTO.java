package com.hyundairoad.hyundairoad.member.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class FindPwDTO {
    private String name;
    @Email
    private String email;
}
