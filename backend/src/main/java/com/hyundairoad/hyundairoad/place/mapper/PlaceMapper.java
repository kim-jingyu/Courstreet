package com.hyundairoad.hyundairoad.place.mapper;

import com.hyundairoad.hyundairoad.place.domain.Place;

import java.util.List;

public interface PlaceMapper {
    List<Place> getListPaging(int limit, int offset);
    List<Place> getList();
    void insert(Place place);

    void delete(Long placeId);
    void update(Place place);
    Place getPlaceByPlaceId(Long placeId);
}
