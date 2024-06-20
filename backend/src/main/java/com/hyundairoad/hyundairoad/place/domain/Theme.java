package com.hyundairoad.hyundairoad.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Theme {
    SNS_HOT_PLACE("SNS 핫플레이스"),
    HEALING_IN_CITY("도심 속 힐링"),
    SHOPPING_HOLIC("쇼핑 홀릭"),
    VISITING_GOOD_RESTAURANTS("맛집 탐방"),
    CHARGING_CAFFEINE("카페인 충전");

    private final String themeName;
}
