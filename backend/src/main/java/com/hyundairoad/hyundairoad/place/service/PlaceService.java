package com.hyundairoad.hyundairoad.place.service;

import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarDto;
import com.hyundairoad.hyundairoad.place.domain.vo.Place;

import java.util.List;

public interface PlaceService {

    /**
     * 장소 전체 조회
     * @return
     */
    List<Place> getAllPlaceList();

    /**
     * 장소 생성
     * @param place
     */
    void register(Place place);

    /**
     * 장소 삭제
     * @param placeId
     */
    void remove(Long placeId);

    /**
     * 장소 수정
     */
    void modify(Place place);

    /**
     * 장소 ID로 하나의 장소 조회
     */
    PlaceStarDto getPlaceByPlaceId(Long placeId);


    /**
     * 장소 + 평점 조회
     * @return
     */
    List<PlaceStarDto> getPlaceStarList();

    List<PlaceStarDto> getPlaceStarByMemberId(Long memberId);

}
