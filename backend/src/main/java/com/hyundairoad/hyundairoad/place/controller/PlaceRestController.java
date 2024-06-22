package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.dto.LikedPlaceDTO;
import com.hyundairoad.hyundairoad.place.domain.dto.PlaceStarLikeDTO;
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
    public ResponseEntity<List<PlaceStarLikeDTO>> getAllPlaces() {
        List<PlaceStarLikeDTO> PlaceList = placeService.getAllLikedPlacesByMemberId(null);
        return ResponseEntity.ok(PlaceList);
    }

    /**
     * 좋아요한 장소 가져오기
     * @return
     */
    // TODO 로그인 정보로 memberId 가져오도록 변경
    @GetMapping("/mylike")
    public ResponseEntity<List<LikedPlaceDTO>> getLikePlaceMemberId() {
        Long memberId = 7L;
        List<LikedPlaceDTO> PlaceList = placeService.getLikedPlacesByMemberId(memberId);
        return ResponseEntity.ok(PlaceList);
    }

}