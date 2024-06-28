package com.hyundairoad.star.controller;

import com.hyundairoad.auth.Auth;
import com.hyundairoad.auth.MemberOnly;
import com.hyundairoad.auth.domain.Accessor;
import com.hyundairoad.place.service.PlaceService;
import com.hyundairoad.star.domain.dto.StarRequest;
import com.hyundairoad.star.service.StarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 별점 컨트롤러
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
@Tag(name = "별점", description = "별점 API입니다.")
@RestController
@RequestMapping("/star")
@RequiredArgsConstructor
public class StarController {
    private final StarService starService;
    private final PlaceService placeService;


    /**
     * 장소에 별점을 추가합니다.
     *
     * @param accessor 회원 정보
     * @param request 별점 요청 정보
     * @return 처리 결과
     */
    @Operation(summary = "장소에 별점을 추가합니다.", description = "회원이 특정 장소에 별점을 추가하는 API입니다.")
    @MemberOnly
    @PostMapping("/place")
    public ResponseEntity<Void> starPlace(@Auth Accessor accessor, @RequestBody StarRequest request) {
        Long memberId = accessor.getMemberId();
        return ResponseEntity.ok().body(starService.starPlace(memberId, request.placeId(), request.rate()));
    }

    /**
     * 특정 장소의 평균 별점을 조회합니다.
     *
     * @param id 장소 ID
     * @return 장소의 평균 별점
     */
    @Operation(summary = "특정 장소의 평균 별점을 조회합니다.", description = "특정 장소의 평균 별점을 조회하는 API입니다.")
    @GetMapping("/{id}/avg")
    public ResponseEntity<Double> getPlaceStartAvgRate(@PathVariable Long id) {
        return ResponseEntity.ok().body(starService.getAvgRate(id));
    }
}
