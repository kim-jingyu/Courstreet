package com.hyundairoad.member.domain;

import com.hyundairoad.member.exception.GenderNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Gender
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String value;

    public static Gender from(String other) {
        return Arrays.stream(values())
                .filter(gender -> gender.value.equals(other))
                .findFirst()
                .orElseThrow(GenderNotFoundException::new);
    }
}
