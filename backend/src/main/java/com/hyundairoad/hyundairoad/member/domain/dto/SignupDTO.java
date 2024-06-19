package com.hyundairoad.hyundairoad.member.domain.dto;

import com.hyundairoad.hyundairoad.member.domain.vo.SEX;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignupDTO {
    private MultipartFile memberImage;
    @Email
    private String email;
    @Min(value = 8)
    private String password;
    private String name;
    private String nickname;
    private SEX sex;
    private Integer age;
}
