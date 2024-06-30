package com.hyundairoad.place.controller;

import com.hyundairoad.auth.admin.AdminOnly;
import com.hyundairoad.auth.member.MemberCheck;
import com.hyundairoad.auth.member.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.place.domain.PlaceResponse;
import com.hyundairoad.place.domain.dto.CreatePlaceRequest;
import com.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import com.hyundairoad.place.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 장소 컨트롤러
 *
 * 작성자: 김진규, 조희정
 */
@Tag(name = "장소", description = "장소 API입니다.")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @Operation(summary = "모든 장소를 조회합니다.", description = "모든 장소를 조회하는 API입니다.")
    @GetMapping("/places")
    public ResponseEntity<List<PlaceResponse>> getPlaceList() {
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    @Operation(summary = "특정 장소를 조회합니다.", description = "특정 장소 이름이 들어간 모든 장소를 조회하는 API입니다.")
    @GetMapping("/place")
    public ResponseEntity<List<PlaceResponse>> getPlaceSearchedList(@RequestParam String placeName) {
        return ResponseEntity.ok().body(placeService.getSearchedPlaces(placeName));
    }

    @Operation(summary = "새로운 장소를 생성합니다.", description = "새로운 장소를 생성하는 API입니다.")
    @MemberOnly
    @PostMapping("/place")
    public ResponseEntity<Long> createPlace(@MemberCheck Accessor accessor, @ModelAttribute @Validated CreatePlaceRequest createPlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.createPlace(createPlaceRequest));
    }

    @Operation(summary = "특정 장소를 수정합니다.", description = "특정 장소를 수정하는 API입니다.")
    @AdminOnly
    @PatchMapping("/place/{id}")
    public ResponseEntity<Void> updatePlace(@MemberCheck Accessor accessor, @PathVariable Long id, @ModelAttribute @Validated UpdatePlaceRequest updatePlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.updatePlace(id, updatePlaceRequest));
    }
}
