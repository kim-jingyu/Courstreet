package com.hyundairoad.hyundairoad.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SEX {
    MALE("남자"),
    FEMALE("여자");

    private final String value;
}
