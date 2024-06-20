package com.hyundairoad.hyundairoad.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WithWhom {
    ALONE("혼자"),
    FRIEND("친구"),
    LOVE("연인"),
    CHILDREN("아이"),
    PARENTS("부모님");

    private final String withWhom;
}
