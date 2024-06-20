package com.hyundairoad.hyundairoad.course.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {
    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String value;
}
