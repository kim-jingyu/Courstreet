package com.hyundairoad.hyundairoad.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {
    MALE("M"),
    FEMALE("F");

    private final String value;
}
