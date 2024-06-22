package hyundairoad.hyundairoad.place.domain;

import hyundairoad.hyundairoad.place.exception.ThemeNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Theme {
    SNS_HOT_PLACE("SNS 핫플레이스"),
    HEALING_IN_CITY("도심 속 힐링"),
    SHOPPING_HOLIC("쇼핑 홀릭"),
    VISITING_GOOD_RESTAURANTS("맛집 탐방"),
    CHARGING_CAFFEINE("카페인 충전");

    private final String value;

    public static Theme from(String other) {
        return Arrays.stream(values())
                .filter(theme -> theme.value.equals(other))
                .findFirst()
                .orElseThrow(ThemeNotFoundException::new);
    }
}
