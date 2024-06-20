package com.hyundairoad.hyundairoad.place.mapper;

import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarDto;
import com.hyundairoad.hyundairoad.place.domain.vo.Place;

import java.util.List;

public interface PlaceMapper {
    List<Place> selectAllPlaceList();

    void insert(Place place);

    void delete(Long placeId);

    void update(Place place);

    List<PlaceStarDto> selectPlaceStarList();

    List<PlaceStarDto> selectPlaceStarByMemberId(Long memberId);

    PlaceStarDto selectPlaceStarByPlaceId(Long placeId);
}
