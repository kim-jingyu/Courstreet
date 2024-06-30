package com.hyundairoad.member.domain;

import com.hyundairoad.place.exception.WithWhomNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * WithWhom
 *
 * 작성자: 김진규
 */
@Getter
@RequiredArgsConstructor
public enum WithWhom {
    FRIEND("친구"),
    PARENT("부모님"),
    LOVE("연인"),
    CHILDREN("아이"),
    ALONE("혼자");

    private final String value;

    public static WithWhom from(String other) {
        return Arrays.stream(values())
                .filter(gender -> gender.value.equals(other))
                .findFirst()
                .orElseThrow(WithWhomNotFoundException::new);
    }
}
