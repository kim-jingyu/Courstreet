package com.hyundairoad.mail.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Mail {
    private String email;
    private String title;
    private String content;
}
