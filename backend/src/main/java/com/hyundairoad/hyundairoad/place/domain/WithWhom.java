package com.hyundairoad.hyundairoad.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WithWhom {
    ALONE("혼자"),
    FRIEND("친구와"),
    LOVE("연인과"),
    CHILDREN("아이와"),
    PARENTS("부모님과");

    private final String withWhom;
}
