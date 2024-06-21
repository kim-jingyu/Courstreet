package com.hyundairoad.hyundairoad.place.mapper;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarLikeDTO;

import java.util.List;

public interface PlaceMapper {

    void insert(Place place);

    void update(Place place);

    void delete(Long placeId);

    List<Place> selectAllPlaceList();

    List<PlaceStarLikeDTO> selectAllPlaceStarList(Long memberId);

    List<LikedPlaceDTO> selectPlaceStarByMemberId(Long memberId);
}
