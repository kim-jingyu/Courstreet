package com.hyundairoad.mail.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessage {
    private String receiver;
    private String title;
    private String content;
}
