package com.hyundairoad.hyundairoad.place.mapper;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PlaceMapper {
    List<Place> getList();
    void insert(Place place);
    void delete(Long placeId);
    void update(Place place);
    Place getPlaceByPlaceId(Long placeId);
    List<LikedPlaceDTO> getLikedPlacesByMemberId(Long memberId);
}
