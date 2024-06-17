package com.hyundairoad.hyundairoad.place.service;

import com.hyundairoad.hyundairoad.place.domain.Place;

import java.util.List;

public interface PlaceService {

    // 전체 조회
    List<Place> getList();

    // 추가
    void register(Place place);

    // 삭제

    // 수정

}
