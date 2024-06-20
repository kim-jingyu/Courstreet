package com.hyundairoad.hyundairoad.course.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {
    PUBLIC("Y"),
    PRIVATE("N");

    private final String value;
}
