package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarDto;
import com.hyundairoad.hyundairoad.place.domain.vo.Place;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/place", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class PlaceRestController {
    private final PlaceService placeService;

    /**
     * 모든 장소 검색
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PlaceStarDto>> getAllPlaces() {
        List<PlaceStarDto> PlaceList = placeService.getPlaceStarList();
        return ResponseEntity.ok(PlaceList);
    }

    /**
     * 좋아요한 장소 가져오기
     * @return
     */
    // TODO 로그인 정보로 memberId 가져오도록 변경
    @GetMapping("/mylike")
    public ResponseEntity<List<PlaceStarDto>> getLikePlaceMemberId() {
        Long memberId = 7L;
        List<PlaceStarDto> PlaceList = placeService.getPlaceStarByMemberId(memberId);
        return ResponseEntity.ok(PlaceList);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceStarDto> getPlaceByPlaceId(@PathVariable Long placeId) {
        PlaceStarDto place = placeService.getPlaceByPlaceId(placeId);
        return ResponseEntity.ok(place);
    }
}