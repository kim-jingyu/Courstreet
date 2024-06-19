package com.hyundairoad.hyundairoad.member.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignupDTO {
    @Email
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String sex;
    private Integer age;
}
