package com.hyundairoad.hyundairoad.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    RESTAURANT("식당"),
    CAFE("카페"),
    BAKERY("베이커리"),
    SHOPPING("쇼핑"),
    ENTERTAINMENT("엔터테인먼트");

    private final String categoryName;
}
