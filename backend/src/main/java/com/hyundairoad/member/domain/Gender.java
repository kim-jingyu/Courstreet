package com.hyundairoad.member.domain;

import com.hyundairoad.member.exception.GenderNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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
