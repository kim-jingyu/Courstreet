package hyundairoad.hyundairoad.place.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
