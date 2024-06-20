package com.hyundairoad.hyundairoad.member.domain.dto;

import com.hyundairoad.hyundairoad.member.domain.vo.Sex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupDTO {
    @NotBlank(message = "ID를 입력해주세요.")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Min(value = 8)
    private String password;
    private String name;
    private String nickname;
    private Sex sex;
    private Integer age;
}
