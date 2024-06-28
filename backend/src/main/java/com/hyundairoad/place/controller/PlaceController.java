package com.hyundairoad.place.controller;

import com.hyundairoad.auth.AdminOnly;
import com.hyundairoad.auth.Auth;
import com.hyundairoad.auth.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.place.domain.PlaceResponse;
import com.hyundairoad.place.domain.dto.CreatePlaceRequest;
import com.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import com.hyundairoad.place.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 장소 컨트롤러
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Tag(name = "장소", description = "장소 API입니다.")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    /**
     * 모든 장소를 조회합니다.
     *
     * @return 모든 장소의 리스트
     */
    @Operation(summary = "모든 장소를 조회합니다.", description = "모든 장소를 조회하는 API입니다.")
    @GetMapping("/places")
    public ResponseEntity<List<PlaceResponse>> getPlaceList() {
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    /**
     * 특정 장소를 조회합니다.
     *
     * @param placeName 장소 이름
     * @return 특정 장소 이름이 들어간 모든 장소의 리스트
     */
    @Operation(summary = "특정 장소를 조회합니다.", description = "특정 장소 이름이 들어간 모든 장소를 조회하는 API입니다.")
    @GetMapping("/place")
    public ResponseEntity<List<PlaceResponse>> getPlaceSearchedList(@RequestParam String placeName) {
        return ResponseEntity.ok().body(placeService.getSearchedPlaces(placeName));
    }

    /**
     * 새로운 장소를 생성합니다.
     *
     * @param accessor 회원 정보
     * @param createPlaceRequest 장소 생성 요청 정보
     * @return 생성된 장소의 ID
     * @throws IOException 입출력 예외
     */
    @Operation(summary = "새로운 장소를 생성합니다.", description = "새로운 장소를 생성하는 API입니다.")
    @MemberOnly
    @PostMapping("/place")
    public ResponseEntity<Long> createPlace(@Auth Accessor accessor, @ModelAttribute CreatePlaceRequest createPlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.createPlace(createPlaceRequest));
    }

    /**
     * 특정 장소를 수정합니다.
     *
     * @param accessor 회원 정보
     * @param updatePlaceRequest 장소 수정 요청 정보
     * @return 수정 결과
     * @throws IOException 입출력 예외
     */
    @Operation(summary = "특정 장소를 수정합니다.", description = "특정 장소를 수정하는 API입니다.")
    @AdminOnly
    @PutMapping("/place/{id}")
    public ResponseEntity<Void> updatePlace(@Auth Accessor accessor, @ModelAttribute UpdatePlaceRequest updatePlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.updatePlace(updatePlaceRequest));
    }
}
