package com.hyundairoad.mail.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 메일
 *
 * 작성자: 김진규
 */
@Data
@Builder
public class Mail {
    private String email;
    private String title;
    private String content;
}
