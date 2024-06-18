package com.hyundairoad.hyundairoad.place.mapper;

import com.hyundairoad.hyundairoad.place.domain.Place;

import java.util.List;

public interface PlaceMapper {
    List<Place> getList();
    void insert(Place place);

    void delete(Long placeId);
}
