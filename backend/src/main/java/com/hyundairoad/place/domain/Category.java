package com.hyundairoad.place.domain;

import com.hyundairoad.place.exception.CategoryNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Category
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@RequiredArgsConstructor
public enum Category {
    RESTAURANT("식당"),
    CAFE("카페"),
    BAKERY("베이커리"),
    SHOPPING("쇼핑"),
    ENTERTAINMENT("엔터테인먼트");

    private final String value;

    public static Category from(String other) {
        return Arrays.stream(values())
                .filter(category -> category.value.equals(other))
                .findFirst()
                .orElseThrow(CategoryNotFoundException::new);
    }
}
