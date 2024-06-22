package hyundairoad.hyundairoad.place.domain;

import hyundairoad.hyundairoad.place.exception.CategoryNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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
