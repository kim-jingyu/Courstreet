package com.hyundairoad.hyundairoad.place.service;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import com.hyundairoad.hyundairoad.place.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceMapper placeMapper;

    @Transactional(readOnly = true)
    public List<Place> getList() {
        return placeMapper.getList();
    }

    public void register(Place place) {
        System.out.println(place);
        placeMapper.insert(place);
    }

    public void remove(Long placeId) {
        placeMapper.delete(placeId);
    }

    public void update(Place place) {

    }

    @Transactional(readOnly = true)
    public List<LikedPlaceDTO> getLikedPlacesByMemberId(Long memberId) {
        return placeMapper.getLikedPlacesByMemberId(memberId);
    }
}
