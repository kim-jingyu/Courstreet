package com.hyundairoad.mail.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * MailRequest
 *
 * 작성자: 김진규
 */
@Data
public class MailRequest {
    @Email
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String email;
}
