//package com.hyundairoad.hyundairoad.place.service.impl;
//
//import com.hyundairoad.hyundairoad.place.domain.dto.LikePlaceStarDTO;
//import com.hyundairoad.hyundairoad.place.domain.vo.Place;
//import com.hyundairoad.hyundairoad.place.mapper.PlaceMapper;
//import com.hyundairoad.hyundairoad.place.service.PlaceService;
//import lombok.AllArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Log
//@Service
//@AllArgsConstructor
//public class PlaceServiceImpl implements PlaceService {
//
//    @Autowired
//    private PlaceMapper mapper;
//
//
//    @Override
//    public List<Place> getAllPlaceList() {
//        return mapper.selectAllPlaceList();
//    }
//
//    @Override
//    public void register(Place place) {
//        mapper.insert(place);
//    }
//
//    @Override
//    public void remove(Long placeId) {
//        mapper.delete(placeId);
//    }
//
//    @Override
//    public void modify(Place place) {
//        mapper.update(place);
//    }
//
//    @Override
//    public LikePlaceStarDTO getPlaceByPlaceId(Long placeId) {
//
//        return mapper.selectPlaceStarByPlaceId(placeId);
//    }
//
//    @Override
//    public List<LikePlaceStarDTO> getPlaceStarList() {
//        return mapper.selectPlaceStarList();
//    }
//
//    @Override
//    public List<LikePlaceStarDTO> getPlaceStarByMemberId(Long memberId) {
//        return mapper.selectPlaceStarByMemberId(memberId);
//    }
//}
