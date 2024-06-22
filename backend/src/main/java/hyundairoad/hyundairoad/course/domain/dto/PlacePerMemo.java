package hyundairoad.hyundairoad.course.domain.dto;

import lombok.Builder;

@Builder
public record PlacePerMemo(Long placeId, String memo) {
}
