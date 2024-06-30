package com.hyundairoad.place.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ThemeWeight
 *
 * 작성자: 김진규, 조희정
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ThemeWeight {
    @Enumerated(value = EnumType.STRING)
    private Theme theme;
    private int weight;
}
